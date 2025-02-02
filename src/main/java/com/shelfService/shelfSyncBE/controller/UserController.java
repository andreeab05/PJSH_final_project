package com.shelfService.shelfSyncBE.controller;

import com.shelfService.shelfSyncBE.entity.User;
import com.shelfService.shelfSyncBE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<User> getUserByUid(@RequestParam Integer uid) {
        User user = userService.getUserByUid(uid);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/updateDescription")
    public ResponseEntity<String> updateDescriptionByUid(@RequestParam Integer uid, @RequestBody String description) {
        String response = userService.updateDescriptionByUid(uid, description);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity<Void> createUser(@RequestParam String username, @RequestParam String role, @RequestBody String description) {
        if(role.equals("author"))
            userService.createAuthor(username, description);
        else
            userService.createReader(username, description);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(@RequestParam Integer uid) {
        String response = userService.deleteUserByUid(uid);
        if(response.equals("User not found"))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
