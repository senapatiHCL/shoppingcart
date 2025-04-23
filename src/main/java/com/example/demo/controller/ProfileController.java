package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.NoDataFoundException;
import com.example.demo.model.ResponseBean;
import com.example.demo.model.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProfileService;
import com.example.demo.service.RegistrationService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/profile/")
public class ProfileController {
	
	Logger logger=LoggerFactory.getLogger(ProfileController.class);
	
	@Autowired
	private ProfileService profileService;
	
	 @Autowired
	 private  RegistrationService registrationService;
	 
@GetMapping("viewProfileById")
public ResponseEntity<?> getProfileById(@RequestParam String profiileId) throws NoDataFoundException {
	logger.debug("inside getProfileById() begine");
		return ResponseEntity.ok(profileService.getUserById(profiileId));	
}
 
@PutMapping("/updateProfile")
public ResponseEntity<?> updateUser(@Valid @RequestBody User user) {
	 registrationService.findUserById(user.getId()); // if not present will throw exception in service layer
    return ResponseEntity.ok(registrationService.registerUser(user));
}
}