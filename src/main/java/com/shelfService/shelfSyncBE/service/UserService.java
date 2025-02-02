package com.shelfService.shelfSyncBE.service;

import com.shelfService.shelfSyncBE.entity.Author;
import com.shelfService.shelfSyncBE.entity.Reader;
import com.shelfService.shelfSyncBE.entity.User;
import com.shelfService.shelfSyncBE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByUid(Integer uid) {
        User user = userRepository.findByUid(uid);
        return user;
    }

    public String updateDescriptionByUid(Integer uid, String description) {
        User user = userRepository.findByUid(uid);
        if(user == null)
            return "No user found";

        // Update description for uid
        user.setDescription(description);
        userRepository.save(user);
        return "User description updated successfully";
    }

    public User createReader(String username, String description) {
        User user = new Reader();
        user.setDescription(description);
        user.setUsername(username);
        return userRepository.save(user);
    }

    public User createAuthor(String username, String description) {
        User user = new Author();
        user.setDescription(description);
        user.setUsername(username);
        return userRepository.save(user);
    }
    public String deleteUserByUid(Integer uid) {
        User user = userRepository.findByUid(uid);
        if(user == null)
            return "User not found";
        userRepository.deleteByUid(uid);
        return "User deleted successfully";
    }

}
