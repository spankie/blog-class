package com.facebook.facebook_mini_clone.service.serviceImpl;

import com.facebook.facebook_mini_clone.model.Comment;
import com.facebook.facebook_mini_clone.model.Like;
import com.facebook.facebook_mini_clone.model.Post;
import com.facebook.facebook_mini_clone.model.User;
import com.facebook.facebook_mini_clone.repo.PostRepo;
import com.facebook.facebook_mini_clone.service.PostService;
import com.facebook.facebook_mini_clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {

    private PostRepo postRepo;
    private UserService userService;

    @Autowired
    public PostServiceImpl(PostRepo postRepo, UserService userService) {
        this.postRepo = postRepo;
        this.userService = userService;
    }

//    @Override
//    public Post addAPost(String lastName, Post postDTO) {
//        Post post = new Post();
//
//
//
//        User user;
//        try {
//             user = userService.getUserByLastName(lastName);
//             post.setUser(user);
//             postRepo.save(post);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    @Override
    public Post addAPost(Post post) {
        return postRepo.save(post);
    }

    @Override
    public Post getPostById(long id) {

        return postRepo.findByPostId(id);
//        Post post =  postRepo.findById(id).orElse(null);
//
//        Set<Comment> comments = post.getComments();
//
//        for (Comment comment : comments) {
//            Set<Like> likes = comment.getLikes();
//        }
//
//
//        return post;
    }

    @Override
    public List<Post> getPostsByUser(User user) {
        List<Post> posts = postRepo.findAllByUser(user);
        Collections.reverse(posts);
        return posts;
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        Collections.reverse(posts);
        return posts;
    }

    @Override
    public Post editPost(Post post, String messageBody) {
        post.setPostContent(messageBody);
        return postRepo.save(post);
    }

    @Override
    public void deletePost(Post post) {
        postRepo.delete(post);
    }
}
