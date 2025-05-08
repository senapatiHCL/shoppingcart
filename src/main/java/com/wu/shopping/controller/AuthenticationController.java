package com.wu.shopping.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wu.shopping.constant.WUConstant;
import com.wu.shopping.dto.LoginUserDto;
import com.wu.shopping.dto.UserDTO;
import com.wu.shopping.dto.UserDTOUS;
import com.wu.shopping.exception.EmailAlreadyExistException;
import com.wu.shopping.model.Address;
import com.wu.shopping.model.User;
import com.wu.shopping.service.RegistrationService;
import com.wu.shopping.service.jwt.AuthenticationService;
import com.wu.shopping.service.jwt.JwtService;

import jakarta.validation.Valid;

@CrossOrigin(origins = WUConstant.CORS_DOMAIN)
@RequestMapping(WUConstant.AUTHENTICATION_CONTROLLER_CONTEXT)
@RestController
public class AuthenticationController {
	
    @Autowired
    private AuthenticationService authenticationService;
    
    @Autowired
    private RegistrationService registrationService;

    @PostMapping(value=WUConstant.US_SIGNUP_URL,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUs(@Valid @RequestBody UserDTOUS user) {
    	 if(registrationService.existsByEmail(user.getEmail())) {
         	throw new EmailAlreadyExistException("This email Already Exists");
         }
    	 registrationService.registerUsUser(user);
        Map responseMap = new HashMap<>();
        responseMap.put("description", "User Registered successfully");
        responseMap.put("status", HttpStatus.OK.value());
        return new ResponseEntity<>(responseMap,HttpStatus.OK);

       
    }
    
    @PostMapping(value=WUConstant.IN_SIGNUP_URL,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerIn(@Valid @RequestBody UserDTO user) {
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
    @PostMapping(value=WUConstant.LOGIN_URL,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticate(@RequestBody LoginUserDto loginUserDto) {

        Map responseMap = new HashMap<>();
        responseMap.put("description", authenticationService.authenticate(loginUserDto));
        responseMap.put("status", HttpStatus.OK.value());
        return new ResponseEntity<>(responseMap,HttpStatus.OK);
     //   return ResponseEntity.ok(loginResponse);
    }}