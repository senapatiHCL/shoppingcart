package com.wu.shopping.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wu.shopping.dto.LoginUserDto;
import com.wu.shopping.dto.UserDTO;
import com.wu.shopping.exception.EmailAlreadyExistException;
import com.wu.shopping.model.Address;
import com.wu.shopping.model.User;
import com.wu.shopping.service.RegistrationService;
import com.wu.shopping.service.jwt.AuthenticationService;
import com.wu.shopping.service.jwt.JwtService;
import com.wu.shopping.service.jwt.LoginResponse;

import jakarta.validation.Valid;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
	@Autowired
    private  JwtService jwtService;
	
    @Autowired
    private AuthenticationService authenticationService;
    
    @Autowired
    private RegistrationService registrationService;

    @PostMapping(value="/signup",consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO user) {
    	 if(registrationService.existsByEmail(user.getEmail())) {
         	throw new EmailAlreadyExistException("This email Already Exists");
         }
    	 registrationService.registerUser(user);
        Map responseMap = new HashMap<>();
        responseMap.put("description", "User Registered successfully");
        responseMap.put("status", HttpStatus.OK.value());
        return new ResponseEntity<>(responseMap,HttpStatus.OK);
	//	return new ResponseEntity<?>("User Registered successfully", HttpStatus.OK);

       
    }

    @PostMapping(value="/login",consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();//.setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setEmail(authenticatedUser.getEmail());
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setFirstName(authenticatedUser.getFirstName());
        loginResponse.setLastName(authenticatedUser.getLastName());
        loginResponse.setMiddleName(authenticatedUser.getMiddleName());
        loginResponse.setToken(jwtToken);
        Map responseMap = new HashMap<>();
        responseMap.put("description", loginResponse);
        responseMap.put("status", HttpStatus.OK.value());
        return new ResponseEntity<>(responseMap,HttpStatus.OK);
     //   return ResponseEntity.ok(loginResponse);
    }}