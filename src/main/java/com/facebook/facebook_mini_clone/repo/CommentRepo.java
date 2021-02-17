package com.facebook.facebook_mini_clone.repo;

import com.facebook.facebook_mini_clone.model.Comment;
import com.facebook.facebook_mini_clone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
}
