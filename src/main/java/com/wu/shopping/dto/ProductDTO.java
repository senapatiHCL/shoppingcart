package com.wu.shopping.dto;

import com.wu.shopping.model.Category;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	@NotBlank(message = "title is mandatory")
    private String title;
    private float price;
    private String description;
    private String images;
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid qunatity")
    private int qunatity;
    @Valid
    private Category category;
    private String product_id;
}
