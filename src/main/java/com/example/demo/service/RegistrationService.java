package com.example.demo.service;


import com.example.demo.exception.NoDataFoundException;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    UserRepo userRepository;

    public User registerUser(User user) {
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }
    
    public User findUserById(String userId) throws NoDataFoundException {
        return userRepository.findById(userId).orElseThrow(()-> new NoDataFoundException("No Data Found"));
    }
    
    public User updateUser(User user) {
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }
    
//    public User registerUser(User user) {
//        user.setPassword(hashPassword(user.getPassword()));
//        return userRepository.save(user);
//    }
//    private String hashPassword(String password) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder.encode(password) ;
//    }


   }
