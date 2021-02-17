package com.facebook.facebook_mini_clone.controller;

import com.facebook.facebook_mini_clone.model.Comment;
import com.facebook.facebook_mini_clone.model.Like;
import com.facebook.facebook_mini_clone.model.Post;
import com.facebook.facebook_mini_clone.model.User;
import com.facebook.facebook_mini_clone.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class profileController {
    PostService postService;

    public profileController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/profile")
    public String getPosts(Model model, HttpSession session) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) return "redirect:/auth/login";
        model.addAttribute("user", (User) userObj);
        model.addAttribute("userposts", postService.getPostsByUser((User) userObj));
        model.addAttribute("newpost", new Post());
        model.addAttribute("postupdate", new Post());
        model.addAttribute("postdelete", new Post());
        model.addAttribute("newcomment", new Comment());
        model.addAttribute("newpostlike", new Like());
        return "profile";
    }
}
