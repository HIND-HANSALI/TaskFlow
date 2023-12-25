package com.taskflow.controller;

import com.taskflow.dto.TaskDto;
import com.taskflow.handlers.response.ResponseMessage;
import com.taskflow.models.User;
import com.taskflow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity getAllUsers() {
        List<User> users = userService.getAllUsers();
        if(users.isEmpty()) {
            return ResponseMessage.notFound("User not found");
        }else {
            return ResponseMessage.ok(users, "Success");
        }
    }

    @PostMapping
    public ResponseEntity addUser(@Valid @RequestBody User user) {
        User userSaved = userService.saveUser(user);


        if(userSaved  == null) {
            return ResponseMessage.badRequest("User not created");
        }else {
            return ResponseMessage.created(userSaved , "User created successfully");
        }
    }
}
