package com.facebook.facebook_mini_clone.service.serviceImpl;

import com.facebook.facebook_mini_clone.model.Like;
import com.facebook.facebook_mini_clone.repo.LikeRepo;
import com.facebook.facebook_mini_clone.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    private LikeRepo likeRepo;

    @Autowired
    public LikeServiceImpl(LikeRepo likeRepo) {
        this.likeRepo = likeRepo;
    }

    @Override
    public List<Like> getAllLikes() {
        return likeRepo.findAll();
    }

    @Override
    public Like addLike(Like like) {
        // check if this user has liked this post beofre...
        var oldLike = likeRepo.findByUserAndPost(like.getUser(), like.getPost());
        if (oldLike != null) {
            deleteLike(oldLike);
        } else {
            like = likeRepo.save(like);
        }
        return like;
    }

    @Override
    public Like getLike(Like like) {
        return likeRepo.findByUserAndPost(like.getUser(), like.getPost());
    }

    @Override
    public void deleteLike(Like like) {
        likeRepo.delete(like);
    }
}
