package com.wu.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PlaceOrderDto {

	private String userid;
	private String mode;
	private String cardNumber;
	private String cvv;
	private String expiry;
	
	
}
