package com.facebook.facebook_mini_clone.service;

import com.facebook.facebook_mini_clone.model.Comment;
import com.facebook.facebook_mini_clone.model.Like;
import com.facebook.facebook_mini_clone.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment);
    List<Comment> viewAllPostComments(Post post);
    void deleteAComment(Comment comment);
}
