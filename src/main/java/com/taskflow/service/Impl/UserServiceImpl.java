package com.taskflow.service.Impl;

import com.taskflow.enums.Role;
import com.taskflow.handlers.exception.OperationException;
import com.taskflow.models.User;
import com.taskflow.repository.UserRepository;
import com.taskflow.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long Id) {
        return null;
//        return userRepository.findById(Id);
    }

    @Override
    public User saveUser(User user) {
        if(!user.getEmail().contains("@") || !user.getEmail().contains(".")){
            throw new OperationException("Votre email Invalid");

        }
        Optional<User> userEmail=this.userRepository.findByEmail(user.getEmail());
        if(userEmail.isPresent()){
            throw new OperationException("Votre email est déja utilisé");

        }
        String password=this.passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        user.setRole(Role.USER);
        return userRepository.save(user);
    }
}
