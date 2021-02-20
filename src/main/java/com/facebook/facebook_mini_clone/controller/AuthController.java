package com.facebook.facebook_mini_clone.controller;

import com.facebook.facebook_mini_clone.model.User;
import com.facebook.facebook_mini_clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


/**
 * AuthController
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

  private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String register(Model model, HttpSession session) {
        model.addAttribute("invalid", session.getAttribute("invalid"));
        session.removeAttribute("invalid");
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid User user, Model model, HttpSession session) {
        User newUser = userService.getUserByEmail(user.getEmail());
        if (newUser != null) {
            // error message if email provided is already registered
            // model.addAttribute("invalid", "User already exists");
            session.setAttribute("invalid", "user already exists");
            return "redirect:/auth/signup";
        }

        userService.addUser(user);

        //successful registration message
        session.setAttribute("newregistration", "Registration successful!");
        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        var users = this.userService.getAllUsers();
        model.addAttribute("user", new User());

        model.addAttribute("invalid", session.getAttribute("invalid"));
        session.removeAttribute("invalid");

        var newReg = session.getAttribute("newregistration");
        model.addAttribute("newregistration", newReg);

        model.addAttribute("users", users);

        session.removeAttribute("newregistration");

        return "login";
    }

    @PostMapping("/login")
    public String login (HttpSession session, @Valid User user, Model model) {
        User gottenUser = userService.getUserByEmailAndPassWord(user.getEmail(), user.getPassword());
        if (gottenUser == null) {
            //error message if email exists but wrong password provided
            session.setAttribute("invalid", "Invalid email or password");
            return "redirect:/auth/login";
        }
        session.setAttribute("user", gottenUser);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/auth/login";
    }
}