package com.mindre.pensionat.security;


import com.mindre.pensionat.Dtos.UserDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public String getUsers(Model model) {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("users", users);
        return "users/index";
    }

    public String getCreatePage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);

        return "users/CreateUser";
    }


    @PostMapping("/users")
    public String createUser(@Valid @ModelAttribute UserDto userDto, BindingResult result,String group) {
        if (result.hasErrors()) {
            logger.error("Validation errors: {}", result.getAllErrors());
            return "users/CreateUser";
        }

        try {
            ArrayList<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByName(group));

           User newUser = User.builder()
                    .enabled(true)
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .username(userDto.getUsername())
                    .roles(roles)
                    .build();

            userRepository.save(newUser);
            logger.info("Saved User: {}", newUser.getUsername());
        } catch (Exception e) {
            logger.error("Error occurred while creating the User", e);
            return "users/CreateUser";
        }

        return "redirect:/users";
    }


    public String getUpdatePage(Model model, @RequestParam UUID id) {

        try {

            User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find ID"));
            model.addAttribute("user", user);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/users";
        }

        return "users/UpdateUser";
    }

    public String updateUser(@RequestParam UUID id, @Valid @ModelAttribute User updateUser, Model model, BindingResult result) {

        try {
            User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find ID"));
            model.addAttribute("user", user);

            if (result.hasErrors()) {
                return "users/UpdateUser";
            }

            user.setUsername(updateUser.getUsername());
            user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
            user.setEnabled(updateUser.isEnabled());


            model.addAttribute("updateUser", updateUser);

            userRepository.save(user);
            logger.info("Updated User with Username: {}", user.getUsername());

        } catch (Exception e) {
            logger.error("Error While Update User");

        }
        return "redirect:/users";
    }


    public String deleteUser(@RequestParam UUID id) {

        try {

            User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find ID"));
            userRepository.delete(user);
            logger.info("Deleted User with ID: {}", user.getId());

        } catch (Exception e) {
            System.out.println("Error While deleting User" + e.getMessage());
        }
        return "redirect:/users";
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("couldn't find user: " + username);
        }

        return new ConcreteUserDetails(user);

    }
}
















