package com.java.firebase.controllers;

import com.java.firebase.model.User;
import com.java.firebase.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class UserDatabaseController {
    public UserService userService;

    public UserDatabaseController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public String signUpUser(@RequestParam String email,
                             @RequestParam String password,
                             @RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.signUpUser(email, password, user);
    }

    @PostMapping("/signIn")
    public String signInUser(@RequestBody String idToken) {
        return userService.signInUser(idToken);
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestParam String userID) {
        try {
            User user = userService.getUser(userID);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestParam String userID, @RequestBody User newUser) {
        try {
            userService.updateUser(userID, newUser);
            return ResponseEntity.ok("User updated successfully");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user: " + e.getMessage());
        }
    }

    @GetMapping("/testUser")
    public ResponseEntity<String> testUser() {
        return ResponseEntity.ok("User Database Controller is working");
    }
}
