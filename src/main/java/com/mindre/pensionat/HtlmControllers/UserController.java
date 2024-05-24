package com.mindre.pensionat.HtlmControllers;

import com.mindre.pensionat.Dtos.UserDto;
import com.mindre.pensionat.security.BaseController;
import com.mindre.pensionat.security.User;
import com.mindre.pensionat.security.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController extends BaseController {

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
    public String createUser(UserDto userDto,String group ,BindingResult result) {
        return userDetailsService.createUser(userDto,group,result);
    }

    @PostMapping("/update")
    public String getUpdatePage(Model model, UUID id) {
        return userDetailsService.getUpdatePage(model,id);
    }


    @GetMapping("/update")
    public String updateUser(UUID id, UserDto userDto, Model model, BindingResult result) {
        return userDetailsService.updateUser(id,userDto,model,result);
    }

    @GetMapping("/delete")
    public String deleteUser(UUID id) {
        return userDetailsService.deleteUser(id);
    }
}
