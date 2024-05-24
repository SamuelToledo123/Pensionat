package com.mindre.pensionat.security;


import com.mindre.pensionat.Dtos.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public String getUsers(Model model) {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        model.addAttribute("users",users);
        return "users/index";
    }

    public String getCreatePage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);

        return "users/CreateUser";
    }


    public String createUser(@Valid @ModelAttribute UserDto userDto, String group, BindingResult result) {
        if (result.hasErrors()) {
            logger.error("Validation errors: {}", result.getAllErrors());
            return "Users/CreateUser";
        }

        try {
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setEnabled(true);
            user.setRoles(Collections.singletonList(roleRepository.findByName(group)));
            userRepository.save(user);
            logger.info("Saved User: {}", user.getUsername());
        } catch (Exception e) {
            logger.error("Error occurred while creating the User", e);
        }
        return "redirect:/users";
    }

    public String getUpdatePage(Model model,@RequestParam UUID id) {

        try {

            User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Couldn't find ID"));
            model.addAttribute("user", user);

            UserDto userDto = new UserDto();
            userDto.setUsername(user.getUsername());
            userDto.setPassword(user.getPassword());
            userDto.setEnabled(user.isEnabled());


            model.addAttribute("userDto", userDto);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/users";
        }

        return "users/UpdateUser";
    }

    public String updateUser(@RequestParam UUID id, @Valid @ModelAttribute UserDto userDto, Model model, BindingResult result) {

            try {
                User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Couldn't find ID"));
                model.addAttribute("user",user);

                if (result.hasErrors()) {
                    return "users/UpdateUser";
                }

                user.setUsername(userDto.getUsername());
                user.setPassword(userDto.getPassword());
                user.setEnabled(userDto.isEnabled());


                model.addAttribute("userDto" , userDto);

                userRepository.save(user);
                logger.info("Updated User with Username: {}" , user.getUsername());

            } catch (Exception e) {
                logger.error("Error While Update User");

            }
            return "redirect:/users";
        }


    public String deleteUser(@RequestParam UUID id) {

            try {

                User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("Couldn't find ID"));
                userRepository.delete(user);
                logger.info("Deleted User with ID: {}" , user.getId());

            } catch (Exception e) {
                System.out.println("Error While deleting User" + e.getMessage());
            }
            return "redirect:/users";
        }


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new ConcreteUserDetails(user);
    }
}

