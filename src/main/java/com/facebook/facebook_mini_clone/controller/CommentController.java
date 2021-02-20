package com.facebook.facebook_mini_clone.controller;

import com.facebook.facebook_mini_clone.model.Comment;
import com.facebook.facebook_mini_clone.model.User;
import com.facebook.facebook_mini_clone.service.CommentService;
import com.facebook.facebook_mini_clone.service.serviceImpl.PostServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/comment")
public class CommentController {

    PostServiceImpl postService;
    CommentService commentService;

    public CommentController(PostServiceImpl postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    //new comment on a post form home page
    @PostMapping("/new/{id}")
    public String newCommentIndex(@PathVariable("id") int id, HttpSession session, @Valid Comment comment, HttpServletRequest request) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) return "redirect:/auth/login";
        System.out.printf("'{}'", comment.getCommentText());
        if (comment.getCommentText().equals("")) {
            String referer = request.getHeader("Referer");
            return "redirect:"+ referer;
        }

        comment.setPost(postService.getPostById(id));
        comment.setUser((User) userObj);
        commentService.addComment(comment);

        return "redirect:/";
    }

    //new comment on a post form post page
    @PostMapping("/post/new/{id}")
    public String newCommentPost(@PathVariable("id") int id, HttpSession session, @Valid Comment comment, HttpServletRequest request) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) return "redirect:/auth/login";

        System.out.printf("sessiong coomment: '{}'", comment.getCommentText());
        if (comment.getCommentText().equals("")) {
            String referer = request.getHeader("Referer");
            return "redirect:"+ referer;
        }

        comment.setPost(postService.getPostById(id));
        comment.setUser((User) userObj);
        commentService.addComment(comment);
        return "redirect:/post/{id}";
    }
}