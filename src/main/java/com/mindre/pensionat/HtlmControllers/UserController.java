package com.mindre.pensionat.HtlmControllers;

import com.mindre.pensionat.Dtos.UserDto;
import com.mindre.pensionat.security.Role;
import com.mindre.pensionat.security.User;
import com.mindre.pensionat.security.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;

    @GetMapping({"", "/"})
    public String getUsers(Model model) {
        return userDetailsService.getUsers(model);
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        return userDetailsService.getCreatePage(model);
    }

    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute User newUser, @RequestParam String role) {
        return userDetailsService.createUser(newUser, role);
    }

    @GetMapping("/update")
    public String getUpdatePage(Model model, @RequestParam UUID id) {
        return userDetailsService.getUpdatePage(model, id);
    }


    @PostMapping("/update")
    public String updateUser(@RequestParam UUID id, @Valid @ModelAttribute UserDto existingUser, Model model, BindingResult result) {
        return userDetailsService.updateUser(id, existingUser, result, model);
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam UUID id) {
        return userDetailsService.deleteUser(id);
    }
}
