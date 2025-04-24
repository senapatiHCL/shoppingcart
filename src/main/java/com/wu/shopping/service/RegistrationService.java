package com.wu.shopping.service;


import com.wu.shopping.exception.NoDataFoundException;
import com.wu.shopping.model.User;
import com.wu.shopping.repo.RegistrationRepo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    RegistrationRepo registrationRepo;

    public User registerUser(User user) {
        user.setPassword(user.getPassword());
        return registrationRepo.save(user);
    }
    
    public User findUserById(String userId) throws NoDataFoundException {
        return registrationRepo.findById(userId).orElseThrow(()-> new NoDataFoundException("No Data Found"));
    }
    
    public User findUserByEmail(String email) throws NoDataFoundException {
        return registrationRepo.findByEmail(email).orElseThrow(()-> new NoDataFoundException("No Data Found"));
    }
    
    public boolean existsByEmail(String email) throws NoDataFoundException {
        return registrationRepo.existsByEmail(email);
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
