package com.taskflow.service.Impl;

import com.taskflow.dto.UserDTO;
import com.taskflow.enums.Role;
import com.taskflow.handlers.exception.OperationException;
import com.taskflow.handlers.exception.ResourceNotFoundException;
import com.taskflow.mappers.UserMapper;
import com.taskflow.models.User;
import com.taskflow.models.Userdetails;
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
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

//    @Override
//    public UserDTO getUserById(Long Id) {
//        User user =userRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("User id " + Id + " not found"));
//        return userMapper.entityToDto(user);
//    }

    @Override
    public Userdetails saveUser(Userdetails user) {
        if(!user.getEmail().contains("@") || !user.getEmail().contains(".")){
            throw new OperationException("Votre email Invalid");

        }
        Optional<Userdetails> userEmail=this.userRepository.findByEmail(user.getEmail());
        if(userEmail.isPresent()){
            throw new OperationException("Votre email est déja utilisé");

        }
        String password=this.passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        user.setRole(Role.USER);
        return null;
//        return userRepository.save(user);
    }
    @Override
    public boolean isUserManager(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        return "MANAGER".equalsIgnoreCase(user.getRole().toString());
    }

    @Override
    public boolean existsById(Long userId){
        boolean userExist= userRepository.existsById(userId);
        if(userExist){
            return true;
        }
        return false;
    }
}
