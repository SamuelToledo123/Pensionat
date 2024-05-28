package com.mindre.pensionat.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")

public class HomeHtmlController {

@GetMapping("/")
    public String getHomePage() {
    return "layouts/index";
}

}
