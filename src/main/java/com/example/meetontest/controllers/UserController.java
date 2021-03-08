package com.example.meetontest.controllers;

import com.example.meetontest.entities.User;
import com.example.meetontest.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/add")
    public String createUser() {
        return "createUser";
    }

    @PostMapping("/add")
    public String createUser(@RequestParam String username, @RequestParam(defaultValue = "") String about, Model model) {
        User user = new User(username, about);
        userRepository.save(user);
        return "createUser";
    }

    @GetMapping("/all")
    public String viewUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userView";
    }
}
