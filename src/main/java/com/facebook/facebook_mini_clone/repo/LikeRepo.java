package com.facebook.facebook_mini_clone.repo;

import com.facebook.facebook_mini_clone.model.Like;
import com.facebook.facebook_mini_clone.model.Post;
import com.facebook.facebook_mini_clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepo extends JpaRepository<Like, Long> {
    Like findByUserAndPost(User user, Post post);
}
