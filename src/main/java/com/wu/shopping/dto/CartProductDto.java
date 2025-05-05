package com.wu.shopping.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDto {
	
	@NotBlank(message = "userId is mandatory")
    private String userId;
	
	@NotBlank(message = "productId is mandatory")
    private String productId;
	
	//@NotBlank(message = "quantity is mandatory")
	// @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid qunatity")
	 @NotNull
    private int quantity;
}
