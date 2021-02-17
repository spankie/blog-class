package com.facebook.facebook_mini_clone.service;

import com.facebook.facebook_mini_clone.model.Post;
import com.facebook.facebook_mini_clone.model.User;

import java.util.List;


public interface PostService {
    Post addAPost(Post post);
    Post getPostById(long id);
    List<Post> getPostsByUser(User user);
    List<Post> getAllPosts();
    Post editPost(Post post, String messageBody);
    void deletePost(Post post);





}
