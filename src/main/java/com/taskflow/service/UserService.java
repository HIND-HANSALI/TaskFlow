package com.taskflow.service;

import com.taskflow.dto.UserDTO;
import com.taskflow.models.User;
import com.taskflow.models.Userdetails;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean isUserManager(Long userId);
    List<User> getAllUsers();
//    UserDTO getUserById(Long Id);
    Userdetails saveUser(Userdetails user);
    boolean existsById(Long userId);
}
