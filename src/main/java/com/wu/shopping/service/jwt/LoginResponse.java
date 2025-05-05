package com.wu.shopping.service.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginResponse {
    private String token;

    private long expiresIn;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String userid;

    
}