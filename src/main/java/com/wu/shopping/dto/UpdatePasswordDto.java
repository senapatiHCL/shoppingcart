package com.wu.shopping.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordDto {

	@NotBlank
	private String id;
	
	@NotBlank
	@Size(min = 8, message = "old password must be at least 8 characters Long")
	private String oldpassword;
	
	@Size(min = 8, message = "new password must be at least 8 characters Long")
	@NotBlank
	private String newpassword;
	
	@Size(min = 8, message = "new Password must be at least 8 characters Long")
	@NotBlank
	private String confirmPassword;
}
