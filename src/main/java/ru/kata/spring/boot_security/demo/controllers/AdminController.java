package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showAllUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "all_users";
    }

    @GetMapping("/new")
    public String addNewUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @GetMapping("/user/{id}")
    public String showEachUser(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("user", userService.showUserById(id));
        return "user";
    }

    @PostMapping()
    public String addNewUser(@ModelAttribute("user") User user) {
        userService.addNewUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user/{id}/edit")
    public String editUserForm(@PathVariable("id") Integer id, ModelMap model){
        model.addAttribute("user", userService.showUserById(id));
        return "edit";
    }

    @PatchMapping("/user/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Integer id){
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping( "/user/{id}")
    public String removeUser(@PathVariable("id") Integer id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}
