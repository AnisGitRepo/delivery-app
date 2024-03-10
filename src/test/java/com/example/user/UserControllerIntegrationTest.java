package com.example.user;

import com.example.IntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


public class UserControllerIntegrationTest extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testCreateUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        // Set user request properties
        userDTO.setUsername("john");
        userDTO.setFirstname("John");
        userDTO.setLastname("Doe");
        userDTO.setEmail("john.doe@example.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testFindUser() throws Exception {
        User user = User.builder().username("john").firstname("John").lastname("Doe").email("john.doe@example.com").build();
        User savedUser = userRepository.save(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/{id}", savedUser.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testUpdateUser() throws Exception {
        User user = User.builder().username("john").firstname("John").lastname("Doe").email("john.doe@example.com").role(Role.ADMIN).build();
        User savedUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        // Set userDTO properties
        userDTO.setId(savedUser.getId());
        userDTO.setUsername("john");
        userDTO.setFirstname("UpdatedFirstName");
        userDTO.setLastname("UpdatedLastName");
        userDTO.setEmail("john.doe@example.com");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO))
                        .with(SecurityMockMvcRequestPostProcessors.user(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testDeleteUser() throws Exception {
        User user = User.builder().username("john").firstname("John").lastname("Doe").email("john.doe@example.com").build();
        User savedUser = userRepository.save(user);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/{id}", savedUser.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testFindUsers() throws Exception {
        User user = User.builder().username("john").firstname("John").lastname("Doe").email("john.doe@example.com").role(Role.ADMIN).build();
        userRepository.save(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user")
                .with(SecurityMockMvcRequestPostProcessors.user(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

