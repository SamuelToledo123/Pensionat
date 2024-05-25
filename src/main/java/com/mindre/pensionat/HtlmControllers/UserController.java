package com.mindre.pensionat.HtlmControllers;

import com.mindre.pensionat.Dtos.UserDto;
import com.mindre.pensionat.security.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    RoleRepository roleRepository;




    @GetMapping({"", "/"})
    public String getUsers(Model model) {
        return userDetailsService.getUsers(model);
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        return userDetailsService.getCreatePage(model);
    }

    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute UserDto userDto, BindingResult result,String group) {
        return userDetailsService.createUser(userDto, result, group);
    }


    @GetMapping("/update")
    public String getUpdatePage(Model model, @RequestParam UUID id) {
        return userDetailsService.getUpdatePage(model, id);
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam UUID id, @Valid @ModelAttribute User updateUser, Model model, BindingResult result) {
        return userDetailsService.updateUser(id, updateUser, model, result);
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam UUID id) {
        return userDetailsService.deleteUser(id);
    }
}


