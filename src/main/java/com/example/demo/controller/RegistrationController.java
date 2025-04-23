package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.LoginService;
import com.example.demo.service.RegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home/auth")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    LoginService loginService;


    @Autowired
    private UserRepo userRepo;

 //   private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody UserDTO userReq) {
        if(userRepo.existsByEmail(userReq.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already exist.");
        }
        User user = new User();
        user.setEmail(userReq.getEmail());
        user.setFirstName(userReq.getFirstName());
        user.setMiddleName(userReq.getMiddleName());
        user.setLastName(userReq.getLastName());
        user.setPhoneNumber(userReq.getPhoneNumber());
        user.setPassword(userReq.getPassword());

        if(!userReq.getAddress().equals(null)) {
            user.setAddress(userReq.getAddress());
        }
        if(!userReq.getZipCode().equals(null)) {
            user.setZipCode(userReq.getZipCode());
        }

        userRepo.save(user);
        return ResponseEntity.ok("User registered successfully");

    }
}
