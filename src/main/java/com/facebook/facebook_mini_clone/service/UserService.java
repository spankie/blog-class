package com.facebook.facebook_mini_clone.service;

import com.facebook.facebook_mini_clone.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User addUser(User user);
    List<User> getAllUsers();
    User getUserById(long id);
    User getUserByEmail(String email);
    User getUserByEmailAndPassWord(String email, String passWord);
    boolean deleteUser(User user);
}
