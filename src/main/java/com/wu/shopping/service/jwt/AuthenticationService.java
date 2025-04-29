package com.wu.shopping.service.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wu.shopping.dto.LoginUserDto;
import com.wu.shopping.dto.UserDTO;
import com.wu.shopping.model.Address;
import com.wu.shopping.model.User;
import com.wu.shopping.repo.RegistrationRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {
	@Autowired
    private RegistrationRepo registrationRepo;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
    private AuthenticationManager authenticationManager;


    public User signup(UserDTO user) {
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
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
        return registrationRepo.save(registeringUser);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.getEmail(),
                input.getPassword()
            )
        );

        return registrationRepo.findByEmail(input.getEmail()).orElseThrow();
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        registrationRepo.findAll().forEach(users::add);

        return users;
    }
}
