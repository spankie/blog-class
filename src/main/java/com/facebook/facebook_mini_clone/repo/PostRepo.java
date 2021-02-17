package com.facebook.facebook_mini_clone.repo;

import com.facebook.facebook_mini_clone.model.Post;
import com.facebook.facebook_mini_clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findAllByUser(User user);
    Post findByPostId(long postId);
}
