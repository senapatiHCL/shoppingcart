package com.wu.shopping.dto;

import java.util.Collection;
import java.util.List;

import com.wu.shopping.model.Address;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOUS {

	@Pattern(regexp = "^[a-zA-Z]*$", message = "Only Alphabets Allowed")
    private String firstName;

	@Pattern(regexp = "^[a-zA-Z]*$", message = "Only Alphabets Allowed")
    private String middleName;

	@Pattern(regexp = "^[a-zA-Z]*$", message = "Only Alphabets Allowed")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters Long")
    private String password;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
    private String phoneNumber;
    
    @Valid
    private Address address;

   
}
