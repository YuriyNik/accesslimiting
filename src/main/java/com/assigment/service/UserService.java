package com.assigment.service;
import com.assigment.model.User;
import com.assigment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User getUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(String userId, User updatedUser) {
        User existingUser = userRepository.findById(userId).orElse(null);

        if (existingUser != null) {
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
          //  existingUser.setLastLoginTimeUtc(LocalDateTime.now());
            return userRepository.save(existingUser);
        }

        return null; // User not found
    }
    public User updateLastLoginTime(User user){
        user.setLastLoginTimeUtc(LocalDateTime.now());
        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
