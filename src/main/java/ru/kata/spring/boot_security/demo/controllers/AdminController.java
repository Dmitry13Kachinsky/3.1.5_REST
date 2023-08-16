package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/page")
    public String printUsers(ModelMap model, Principal principal) {
        model.addAttribute("userAuth", userService.findByUsername(principal.getName()));
        return "index";
    }
}
