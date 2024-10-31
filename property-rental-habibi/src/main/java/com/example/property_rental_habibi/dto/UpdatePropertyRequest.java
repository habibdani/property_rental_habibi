package com.example.property_rental_habibi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class UpdatePropertyRequest {

    @Size(max = 100, message = "The p_name must be less than or equal to 100 characters")
    private String name;

    @Size(max = 255, message = "The p_location must be less than or equal to 255 characters")
    private String location;

    @DecimalMin(value = "0.0", inclusive = false, message = "The p_price must be greater than 0")
    private BigDecimal price;

    @Size(max = 5000, message = "The p_description must be less than or equal to 5000 characters")
    private String description;

}
