package com.wu.shopping.service;

import com.wu.shopping.model.User;
import com.wu.shopping.repo.RegistrationRepo;

//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {

	/*
	 * @Autowired UserRepository userRepository; private final String jwtSecret =
	 * "your-secret-key"; private final int jwtExpiration = 360000;
	 * 
	 * public String login(String email, String password) { User user
	 * =userRepository.findByEmail(email) .orElseThrow(()->new
	 * RuntimeException("user not found"));
	 * 
	 * if(!password.equals(user.getPassword())) { throw new
	 * RuntimeException("Invalid credentials"); }
	 * 
	 * return generateToke(user); }
	 * 
	 * private String generateToke(User user) { return Jwts.builder()
	 * .setSubject(user.getEmail()) .setIssuedAt(new Date()) .setExpiration(new
	 * Date(System.currentTimeMillis() + jwtExpiration))
	 * .signWith(SignatureAlgorithm.HS512,jwtSecret) .compact(); }
	 * 
	 */
}
