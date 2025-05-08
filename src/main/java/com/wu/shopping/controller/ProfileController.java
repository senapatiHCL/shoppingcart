package com.wu.shopping.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wu.shopping.dto.AddressDto;
import com.wu.shopping.dto.UpdatePasswordDto;
import com.wu.shopping.dto.UserDTO;
import com.wu.shopping.exception.NoDataFoundException;
import com.wu.shopping.model.Address;
import com.wu.shopping.model.User;
import com.wu.shopping.service.RegistrationService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*",allowCredentials= "true")
@RestController
@RequestMapping("/profile/")
public class ProfileController {
	
	Logger logger=LoggerFactory.getLogger(ProfileController.class);
	
	
	 @Autowired
	 private  RegistrationService registrationService;
	 
@GetMapping(value="viewProfileByEmail")
public ResponseEntity<?> findUserByEmail(@RequestParam String email) throws NoDataFoundException {
	logger.info("inside getProfileById() begine");
	 Map responseMap = new HashMap<>();
	 responseMap.put("description", registrationService.findUserByEmail(email));
     responseMap.put("status", HttpStatus.OK.value());
     return new ResponseEntity<>(responseMap,HttpStatus.OK);
}

@GetMapping(value="viewProfileById")
public ResponseEntity<?> viewProfileById(@RequestParam String userId) throws NoDataFoundException {
	logger.info("inside getProfileById() begine");
	 Map responseMap = new HashMap<>();
	 responseMap.put("description", registrationService.findUserById(userId));
     responseMap.put("status", HttpStatus.OK.value());
     return new ResponseEntity<>(responseMap,HttpStatus.OK);
}
 
@PutMapping(value="/updateProfile")
public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO user) {
  registrationService.updateUser(user);
  Map responseMap = new HashMap<>();
	 responseMap.put("description", "User Detail Updated Successfully");
responseMap.put("status", HttpStatus.OK.value());
  return new ResponseEntity<>(responseMap,HttpStatus.OK);
}

@PutMapping(value="/setNewPassword")
public ResponseEntity<?> setNewPassword(@Valid @RequestBody UpdatePasswordDto updatePasswordDto) {
  registrationService.setNewPassword(updatePasswordDto);
  Map responseMap = new HashMap<>();
	 responseMap.put("description", "Password Updated Successfully");
responseMap.put("status", HttpStatus.OK.value());
  return new ResponseEntity<>(responseMap,HttpStatus.OK);
}

@GetMapping(value="/getAddress")
public ResponseEntity<?> updateUser(@RequestParam String userId) {
 List<Address> add= registrationService.findUserById(userId).getAddress();
  Map responseMap = new HashMap<>();
	 responseMap.put("shippingAddress", add);
responseMap.put("status", HttpStatus.OK.value());
  return new ResponseEntity<>(responseMap,HttpStatus.OK);
//	return ResponseEntity.ok(registrationService.updateUser(user));
}

@PostMapping(value="/saveNewAddress")
public ResponseEntity<?> saveNewAddress(@Valid @RequestBody AddressDto addressDto) {
	registrationService.saveNewAddress(addressDto);
 
  Map responseMap = new HashMap<>();
	 responseMap.put("shippingAddress", "shipping Address saved");
responseMap.put("status", HttpStatus.OK.value());
  return new ResponseEntity<>(responseMap,HttpStatus.OK);
//	return ResponseEntity.ok(registrationService.updateUser(user));
}
}