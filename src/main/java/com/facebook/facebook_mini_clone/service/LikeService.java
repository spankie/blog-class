package com.facebook.facebook_mini_clone.service;

import com.facebook.facebook_mini_clone.model.Like;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LikeService {
    List<Like> getAllLikes();
    Like addLike(Like like);
    Like getLike(Like like);
    void deleteLike(Like like);
}
