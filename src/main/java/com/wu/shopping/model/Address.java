package com.wu.shopping.model;

import jakarta.validation.constraints.Pattern;

public class Address {

	@Pattern(regexp = "^[a-zA-Z0-9_\\s]+$", message = "Only AlphaNumeric Allowed")
    private String houseNumber;

    @Pattern(regexp = "^[a-zA-Z0-9_\\s]+$", message = "Only AlphaNumeric Allowed")
    private String street;

    @Pattern(regexp = "^[a-zA-Z]*$", message = "Only Alphabets Allowed")
    private String city;

    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Only Alphabets and Spaces Allowed")
    private String state;

    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Only Alphabets and Spaces Allowed")
    private String country;

    @Pattern(regexp = "\\d{6}", message = "Zip code must be 6 digits")
    private String zipCode;
    
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
    
}
