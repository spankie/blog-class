package com.facebook.facebook_mini_clone.controller;

import com.facebook.facebook_mini_clone.model.User;
import com.facebook.facebook_mini_clone.repo.UserRepo;
import com.facebook.facebook_mini_clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/auth")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String register(Model model) {
        model.addAttribute("invalid", null);
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid User user, Model model) {
        User newUser = userService.getUserByEmail(user.getEmail());
        if (newUser != null) {
            //error message if email provided is already registered
            model.addAttribute("invalid", "User already exists");
            return "signup";
        }

        userService.addUser(user);
        //successful registration message
        model.addAttribute("newregistration", "Registration successful!");
        return "login";
    }
}
