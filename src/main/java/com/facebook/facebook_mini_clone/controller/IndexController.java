package com.facebook.facebook_mini_clone.controller;

import com.facebook.facebook_mini_clone.model.Comment;
import com.facebook.facebook_mini_clone.model.Like;
import com.facebook.facebook_mini_clone.model.Post;
import com.facebook.facebook_mini_clone.model.User;
import com.facebook.facebook_mini_clone.service.LikeService;
import com.facebook.facebook_mini_clone.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {
    PostService postService;
    LikeService likeService;

    public IndexController(PostService postService) {
        this.postService = postService;
    }

    //get mapping for populating home page
    @GetMapping("/")
    public String getPosts(Model model, HttpSession session) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) return "redirect:/auth/login";

        model.addAttribute("user", (User) userObj);

        model.addAttribute("posts", postService.getAllPosts());

        model.addAttribute("newpost", new Post());
        model.addAttribute("newcomment", new Comment());
        model.addAttribute("newlike", new Like());

        return "index";
    }
}

