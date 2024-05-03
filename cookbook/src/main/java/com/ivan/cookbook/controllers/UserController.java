package com.ivan.cookbook.controllers;

import com.ivan.cookbook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.ivan.cookbook.models.User;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {


    @Autowired
    private UserRepository userRepo;

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user, Model model) {
        // Check if the email already exists
        if (userRepo.findByEmail(user.getEmail()) != null) {
            // Set the flag to indicate email exists
            model.addAttribute("emailExists", true);
            // Return the registration form again with the flag set
            return "register";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);
        return "register_success";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
}
