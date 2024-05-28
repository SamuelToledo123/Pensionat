package com.mindre.pensionat.security;

import com.mindre.pensionat.Dtos.UserDto;
import com.mindre.pensionat.Utils.UserMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String getUsers(Model model) {
        List<User> users = (List<User>) userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(user -> UserMapper.toDto(user))
                .collect(Collectors.toList());
        model.addAttribute("users", userDtos);
        return "users/index";
    }

    public String getCreatePage(Model model) {
        model.addAttribute("user",new User());
        return "users/CreateUser";
    }

    public String createUser(User newUser , String role) {
        Role userRole = roleRepository.findByName(role);
        if (userRole == null) {
            userRole = new Role(role);
            roleRepository.save(userRole);
        }

        try {
            newUser = User.builder()
                    .enabled(true)
                    .username(newUser.getUsername())
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .build();

            if (newUser.getRoles() == null) {
                newUser.setRoles(new ArrayList<>());

            } else {
                if (newUser.getRoles().contains(userRole)) {
                    throw new RuntimeException("User already has the role: " + userRole.getName());
                }
            }

            newUser.getRoles().add(userRole);
            userRepository.save(newUser);

            logger.info("Saved User: {}", newUser.getUsername());
        } catch (Exception e) {
            logger.error("Error occurred while creating the User", e);
            return "users/CreateUser";
        }

        return "redirect:/users";
    }


    @GetMapping("/update")
    public String getUpdatePage(Model model, @RequestParam UUID id) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
            model.addAttribute("userDto", UserMapper.toDto(user));
            return "users/UpdateUser";
        } catch (Exception e) {
            logger.error("Error occurred while retrieving user for update", e);
            return "redirect:/users";
        }
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam UUID id, @Valid @ModelAttribute UserDto userDto, BindingResult result, Model model) {
        try {

            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User was not found"));

            existingUser.setUsername(userDto.getUsername());
            existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
            existingUser.setEnabled(true);

            if (result.hasErrors()) {
                logger.error("Validation errors: {}", result.getAllErrors());
                model.addAttribute("user", existingUser);
                return "users/UpdateUser";
            }

            userRepository.save(existingUser);
            logger.info("Updated User with Username: {}", existingUser.getUsername());
        } catch (Exception e) {
            logger.error("Error occurred while updating the user", e);
            return "users/UpdateUser";
        }

        return "redirect:/users";
    }


    public String deleteUser(@RequestParam UUID id) {
        try {
            userRepository.deleteById(id);
            logger.info("Deleted User with the id: {}", id);

    } catch (Exception e) {
        logger.error("Error while Deleting Reservation");
        }
        return "redirect:/users";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Couldn't find user: " + username);
        }

        return new ConcreteUserDetails(user);
    }
}
