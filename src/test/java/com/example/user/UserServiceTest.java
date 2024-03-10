package com.example.user;


import com.example.auth.EmailService;
import com.example.exception.UnauthorizedOperationException;
import com.example.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO("john", "John", "Doe", "john@example.com");
        String randomPassword = "randomPassword";
        User user = new User("john", "John", "Doe", "john@example.com");
        user.setPassword(randomPassword);
        user.setTempPassword(true);
        user.setRole(Role.USER);

        when(passwordEncoder.encode(anyString())).thenReturn(randomPassword);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userDTO);

        userService.createUser(userDTO);

        verify(userMapper, times(1)).toDto(user);
        verify(userRepository, times(1)).save(user);
        verify(emailService, times(1)).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    public void testUpdateUser_WithAdminRole() {
        UserDTO userDTO = new UserDTO("john", "John", "Doe", "john@example.com");
        User user = User.builder().username("john").firstname("John").lastname("Doe").email("john@example.com").build();

        Principal connectedUser = new UsernamePasswordAuthenticationToken(user, null);

        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userDTO);
        userService.updateUser(userDTO, connectedUser);

        verify(userMapper, times(1)).toDto(user);
        verify(userRepository, times(1)).findByUsername(userDTO.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateUser_UnauthorizedOperation() {
        UserDTO userDTO = new UserDTO("john", "John", "Doe", "john@example.com");
        User user = User.builder().username("anotherUsername").firstname("John").lastname("Doe").email("john@example.com").role(Role.USER).build();

        Principal connectedUser = new UsernamePasswordAuthenticationToken(user, null);

        assertThrows(UnauthorizedOperationException.class, () -> userService.updateUser(userDTO, connectedUser));

    }

    @Test
    public void testDeleteUser() {
        User user = new User("john", "John", "Doe", "john@example.com");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        userService.deleteUser(1L);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void testDeleteUser_UserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(1L));

        verify(userRepository, never()).delete(any(User.class));
    }

    @Test
    public void testFindUser() {
        User user = new User("john", "John", "Doe", "john@example.com");
        UserDTO userDTO = new UserDTO("john", "John", "Doe", "john@example.com");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userMapper.toDto(any(User.class))).thenReturn(userDTO);

        UserDTO result = userService.findUser(1L);

        assertEquals("John", result.getFirstname());
        assertEquals("Doe", result.getLastname());
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    public void testFindUser_UserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findUser(1L));
    }

    @Test
    public void testFindUsers() {
        // Mocking connected user
        User connectedUser = new User();
        Principal principal = new UsernamePasswordAuthenticationToken(connectedUser, null);

        // Mocking userRepository methods
        List<User> users = new ArrayList<>();
        users.add(new User("john", "John", "Doe", "john@example.com"));
        users.add(new User("john", "John", "Doe", "john@example.com"));
        when(userRepository.findAll()).thenReturn(users);
        List<UserDTO> userDTOS = new ArrayList<>();
        userDTOS.add(new UserDTO("john", "John", "Doe", "john@example.com"));
        userDTOS.add(new UserDTO("john", "John", "Doe", "john@example.com"));
        when(userMapper.toDto(any(List.class))).thenReturn(userDTOS);

        // Testing findUsers method
        List<UserDTO> userDTOs = userService.findUsers();

        assertEquals(2, userDTOs.size());
    }
}

