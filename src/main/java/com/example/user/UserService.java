package com.example.user;

import com.example.auth.EmailService;
import com.example.exception.UnauthorizedOperationException;
import com.example.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


    @Async
    public UserDTO createUser(UserDTO userDTO){
        var user = User.builder()
                .username(userDTO.getUsername())
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .email(userDTO.getEmail())
                .role(Role.USER)
                .build();
        String randomPassword = UUID.randomUUID().toString();
        user.setPassword(passwordEncoder.encode(randomPassword));
        user.setTempPassword(true);
        user.setRole(Role.USER);
        User savedUser = userRepository.save(user);
        String body = "you account is created and here are your login credentials: \n" +
                "email: "+user.getEmail()+" \n" +
                "password: "+randomPassword+" \n";
        emailService.sendEmail(user.getEmail(), "reset account password", body);
        return userMapper.toDto(savedUser);
    }

    public UserDTO updateUser(UserDTO userDTO, Principal connectedUser){
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if(!user.getUsername().equals(userDTO.getUsername()) && Role.USER.equals(user.getRole())){
            throw new UnauthorizedOperationException("Cannot update another user profile");
        }

        var userFromDb = userRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        userFromDb.setFirstname(userDTO.getFirstname());
        userFromDb.setLastname(userDTO.getLastname());
        userFromDb.setEmail(userDTO.getEmail());
        return userMapper.toDto(userRepository.save(userFromDb));
    }

    public void deleteUser(Long id){
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        userRepository.delete(user);
    }

    public UserDTO findUser(Long id){
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return userMapper.toDto(user);
    }

    public List<UserDTO> findUsers(){
        return userMapper.toDto(userRepository.findAll());
    }


}
