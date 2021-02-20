package com.facebook.facebook_mini_clone.controller;

import com.facebook.facebook_mini_clone.model.Comment;
import com.facebook.facebook_mini_clone.model.Like;
import com.facebook.facebook_mini_clone.model.Post;
import com.facebook.facebook_mini_clone.model.User;
import com.facebook.facebook_mini_clone.service.CommentService;
import com.facebook.facebook_mini_clone.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/post")
public class PostController {

    private CommentService commentService;
    private PostService postService;

    public PostController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    /*
    New post from home page
     */
    @PostMapping("/new")
    public String newPostIndex(HttpSession session, @Valid Post post) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) return "redirect:/auth/login";

        post.setUser((User) userObj);
        postService.addAPost(post);
        return "redirect:/";
    }

    //edit post
    @PostMapping("/edit/{id}")
    public String editPost(@PathVariable("id") long id, HttpSession session, @Valid Post postupdate) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) return "redirect:/auth/login";

        Post post = postService.getPostById(id);
        postService.editPost(post, postupdate.getPostContent());
        return "redirect:/profile";
    }

    //delete post
    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") long id, HttpSession session) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) return "redirect:/auth/login";

        Post post = postService.getPostById(id);
        postService.deletePost(post);
        return "redirect:/profile";
    }

    //get mapping for single post page view
    @GetMapping("/{id}")
    public String viewPost(@PathVariable("id") long id, HttpSession session, Model model) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) return "redirect:/auth/login";
        
        Post post = postService.getPostById(id);

        model.addAttribute("user", (User) userObj);
        model.addAttribute("post", post);
        model.addAttribute("comments", commentService.viewAllPostComments(post));
        model.addAttribute("newcomment", new Comment());
        model.addAttribute("newlike", new Like());

        return "post";
    }

}
