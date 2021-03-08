package com.example.meetontest.controllers;

import com.example.meetontest.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(@RequestParam(name="name", required=false, defaultValue = "anon") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }
}
