package com.taskflow.service;

import com.taskflow.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long Id);
    User saveUser(User user);
}
