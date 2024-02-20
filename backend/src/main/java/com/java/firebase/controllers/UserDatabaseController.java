package com.java.firebase.controllers;

import com.java.firebase.model.User;
import com.java.firebase.service.UserService;
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
    public User getUser(@RequestParam String userID) throws ExecutionException, InterruptedException {
        return userService.getUser(userID);
    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.updateUser(user);
    }

    @PutMapping("/deleteUser")
    public String deleteUser(@RequestParam String userID) throws ExecutionException, InterruptedException {
        return userService.deleteUser(userID);
    }

    @GetMapping("/testUser")
    public ResponseEntity<String> testUser() {
        return ResponseEntity.ok("User Database Controller is working");
    }
}
