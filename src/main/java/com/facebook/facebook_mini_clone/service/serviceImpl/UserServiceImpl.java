package com.facebook.facebook_mini_clone.service.serviceImpl;

import com.facebook.facebook_mini_clone.model.User;
import com.facebook.facebook_mini_clone.repo.UserRepo;
import com.facebook.facebook_mini_clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private UserRepo userRepo;


    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User addUser(User user) {
//        return userRepo.save(user);
        User user1 = userRepo.save(user);
        System.out.println(user1);
        return user1;
    }
//
//    @Override
//    public User getUserByLastName(String lastName) throws Exception {
//        return userRepo.findByLastName(lastName).orElseThrow(() -> new Exception("gbf"));
//    }


    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    @Override
    public User getUserByEmailAndPassWord(String email, String password) {
        return userRepo.findByEmailAndPassword(email, password).orElse(null);
    }

    @Override
    public boolean deleteUser(User user) {
        userRepo.delete(user);
        return true;
    }
}
