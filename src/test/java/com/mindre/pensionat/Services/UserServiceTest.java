package com.mindre.pensionat.Services;

import com.mindre.pensionat.Dtos.UserDto;
import com.mindre.pensionat.security.Role;
import com.mindre.pensionat.security.RoleRepository;
import com.mindre.pensionat.security.User;
import com.mindre.pensionat.security.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @BeforeEach
    void init() {
        userRepository.deleteAll();
        roleRepository.deleteAll();

        UUID uuid1 = UUID.fromString("f2b5e110-83f2-11ec-8d3d-9f531c13d6d8");
        UUID uuid2 = UUID.fromString("2f5b1e01-382f-11ce-d8d3-f935c1316d8d");

        Role admin = Role.builder().name("Admin").build();
        Role receptionist = Role.builder().name("Receptionist").build();

        List<Role> rolesToSave = Arrays.asList(admin, receptionist);
        roleRepository.saveAll(rolesToSave);

        User user1 = User.builder()
                .id(uuid1)
                .username("Test@hotmail.com")
                .password(passwordEncoder.encode("Test"))
                .enabled(true)
                .roles(Collections.singletonList(receptionist))
                .build();

        User user2 = User.builder()
                .id(uuid2)
                .username("Test2@hotmail.com")
                .password(passwordEncoder.encode("Test2"))
                .enabled(true)
                .roles(Collections.singletonList(admin))
                .build();

        List<User> usersToSave = Arrays.asList(user1, user2);
        userRepository.saveAll(usersToSave);
    }

    @Test
    public void getUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        assertEquals(2, users.size());

        assertTrue(users.stream().anyMatch(user -> user.getUsername().equals("Test@hotmail.com")));
        assertTrue(users.stream().anyMatch(user -> user.getUsername().equals("Test2@hotmail.com")));

        assertTrue(users.stream().filter(user -> user.getUsername().equals("Test@hotmail.com"))
                .findFirst().orElseThrow().getRoles().stream()
                .anyMatch(role -> role.getName().equals("Receptionist")));

        assertTrue(users.stream().filter(user -> user.getUsername().equals("Test2@hotmail.com"))
                .findFirst().orElseThrow().getRoles().stream()
                .anyMatch(role -> role.getName().equals("Admin")));
    }

    @Test
    public void createUser() {
        UUID uuid2 = UUID.fromString("2f5b1e01-382f-11ce-d8d3-f935c1316d8d");

        User savedUser = userRepository.getUserByUsername("Test2@hotmail.com");
        assertNotNull(savedUser, "User should be saved and not null");

        assertEquals("Test2@hotmail.com", savedUser.getUsername(), "Username should match");
        assertTrue(passwordEncoder.matches("Test2", savedUser.getPassword()), "Password should match");
        assertTrue(savedUser.getRoles().stream()
                .anyMatch(role -> role.getName().equals("Admin")), "Role should match");
    }

    @Test
    public void updateUser() {

        User existingUser = userRepository.getUserByUsername("Test@hotmail.com");
        assertNotNull(existingUser, "User should be saved and not null");

        UserDto userDto = UserDto.builder()
                .username("UpdatedTest@hotmail.com")
                .password("UpdatedTest")
                .build();

        existingUser.setUsername(userDto.getUsername());
        existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        existingUser.setEnabled(true);

        userRepository.save(existingUser);
        logger.info("Updated User with Username: {}", existingUser.getUsername());

        User updatedUser = userRepository.getUserByUsername("UpdatedTest@hotmail.com");
        assertNotNull(updatedUser, "User should be saved and not null");

        assertEquals("UpdatedTest@hotmail.com", updatedUser.getUsername(), "Username should match");
        assertTrue(passwordEncoder.matches("UpdatedTest", updatedUser.getPassword()), "Password should match");
        assertTrue(updatedUser.getRoles().stream()
                .anyMatch(role -> role.getName().equals("Receptionist")), "Role should match");
    }

    @Test
    public void deleteUser() {
        UUID uuid3 = UUID.fromString("2f1c1e01-386d-11ea-b8a3-f935c1316d8d");

        User user3 = User.builder()
                .id(uuid3)
                .username("Test3@hotmail.com")
                .password(passwordEncoder.encode("Test3"))
                .enabled(true)
                .build();

        userRepository.save(user3);

        userRepository.delete(user3);
        User deletedUser = userRepository.findById(uuid3).orElse(null);
        assertNull(deletedUser, "Deleted User should not exist in the repository");
    }
}
