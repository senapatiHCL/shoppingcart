package com.wu.shopping.dto;

import com.wu.shopping.model.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AddressDto {
	
	@NotBlank(message = "houseNumber is mandatory")
	 private String userId;
	
	@NotBlank(message = "address is mandatory")
	private Address address;
	
	
	 
//	@Pattern(regexp = "^[a-zA-Z0-9_\\s]+$", message = "Only AlphaNumeric Allowed")
//	@NotBlank(message = "houseNumber is mandatory")
//    private String houseNumber;
//
//    @Pattern(regexp = "^[a-zA-Z0-9_\\s]+$", message = "Only AlphaNumeric Allowed")
//    @NotBlank(message = "street Name is mandatory")
//    private String street;
//
//    @Pattern(regexp = "^[a-zA-Z]*$", message = "Only Alphabets Allowed")
//    @NotBlank(message = "city Name is mandatory")
//    private String city;
//
//    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Only Alphabets and Spaces Allowed")
//    @NotBlank(message = "state Name is mandatory")
//    private String state;
//
//    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Only Alphabets and Spaces Allowed")
//    @NotBlank(message = "country Name is mandatory")
//    private String country;
//
//    @Pattern(regexp = "\\d{6}", message = "Zip code must be 6 digits")
//    @NotBlank(message = "zipCode is mandatory")
//    private String zipCode;
//    
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getHouseNumber() {
//        return houseNumber;
//    }
//
//    public void setHouseNumber(String houseNumber) {
//        this.houseNumber = houseNumber;
//    }
//
//    public String getStreet() {
//        return street;
//    }
//
//    public void setStreet(String street) {
//        this.street = street;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//	public String getZipCode() {
//		return zipCode;
//	}
//
//	public void setZipCode(String zipCode) {
//		this.zipCode = zipCode;
//	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
    
}

