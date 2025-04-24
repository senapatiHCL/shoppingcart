package com.wu.shopping.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wu.shopping.exception.NoDataFoundException;
import com.wu.shopping.model.User;
import com.wu.shopping.service.RegistrationService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	 private  RegistrationService registrationService;
	 
@GetMapping(value="viewProfileById",consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> getProfileById(@RequestParam String profiileId) throws NoDataFoundException {
	logger.info("inside getProfileById() begine");
		return ResponseEntity.ok(registrationService.findUserById(profiileId));	
}
 
@PutMapping(value="/updateProfile",consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> updateUser(@Valid @RequestBody User user) {
	User dbuser=registrationService.findUserById(user.getId()); // if not present will throw exception in service layer
  if(! dbuser.getPassword().equalsIgnoreCase(user.getPassword())) {
	  return ResponseEntity.badRequest().body("Right Now updating password not allowed");
  }
	return ResponseEntity.ok(registrationService.registerUser(user));
}
}