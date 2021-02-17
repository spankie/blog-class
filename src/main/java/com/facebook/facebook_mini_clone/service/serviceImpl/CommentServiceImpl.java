package com.facebook.facebook_mini_clone.service.serviceImpl;

import com.facebook.facebook_mini_clone.model.Comment;
import com.facebook.facebook_mini_clone.model.Post;
import com.facebook.facebook_mini_clone.repo.CommentRepo;
import com.facebook.facebook_mini_clone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {


    private CommentRepo commentRepo;

    @Autowired
    public CommentServiceImpl(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepo.save(comment);
    }

    @Override
    public List<Comment> viewAllPostComments(Post post) {
        return commentRepo.findAllByPost(post);
    }


    @Override
    public void deleteAComment(Comment comment) {
        commentRepo.delete(comment);
    }
}
