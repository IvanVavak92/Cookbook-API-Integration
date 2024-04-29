package com.ivan.cookbook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
