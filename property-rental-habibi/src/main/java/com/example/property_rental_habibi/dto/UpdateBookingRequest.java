package com.example.property_rental_habibi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UpdateBookingRequest {

    @NotNull(message = "Property ID cannot be null")
    private Long propertyId;

    @NotNull(message = "Start date cannot be null")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    private LocalDate endDate;

    private String stato;

    public void calculateDuration() {
    }
}
