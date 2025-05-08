package com.wu.shopping.dto;

import com.wu.shopping.model.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PlaceOrderDto {

	@NotBlank(message = "userid is mandatory")
	private String userid;
	@NotBlank(message = "mode is mandatory")
	private String mode;
	
	@Pattern(regexp = "^[0-9-]*$", message = "Invalid cardNumber")
	@Size(min = 19, message = "Invalid cardNumber")
	private String cardNumber;
	
	@Pattern(regexp = "\\d", message = "Invalid qunatity")
	@Size(min = 3, message = "Invalid cvv")
	private String cvv;
	
	@Pattern(regexp = "^[0-9/]*$", message = "Invalid qunatity")
	@Size(min = 5, message = "Invalid expiry")
	private String expiry;
	
	private Address shippingAddress;
	
	
}
