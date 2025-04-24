package com.wu.shopping.controller;


import org.springframework.beans.factory.annotation.Autowired;
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
    	User registeringUser=new User();
    	registeringUser.setEmail(user.getEmail());
    	registeringUser.setFirstName(user.getFirstName());
    	registeringUser.setLastName(user.getLastName());
    	registeringUser.setMiddleName(user.getMiddleName());
    	registeringUser.setPassword(user.getPassword());
    	registeringUser.setPhoneNumber(user.getPhoneNumber());
    	Address add= new Address();
    	add.setCity(user.getAddress().getCity());
    	add.setCountry(user.getAddress().getCountry());
    	add.setHouseNumber(user.getAddress().getHouseNumber());
    	add.setState(user.getAddress().getState());
    	add.setStreet(user.getAddress().getStreet());
    	add.setZipCode(user.getAddress().getZipCode());
    	registeringUser.setAddress(add);
        authenticationService.signup(registeringUser);

        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping(value="/login",consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }}