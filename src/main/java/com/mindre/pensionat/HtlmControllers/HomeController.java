package com.mindre.pensionat.HtlmControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "layouts/index"; // Return the name of your Thymeleaf template
    }
}
