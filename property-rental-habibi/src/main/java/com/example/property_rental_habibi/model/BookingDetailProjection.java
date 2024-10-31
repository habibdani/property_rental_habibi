package com.example.property_rental_habibi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface BookingDetailProjection {
    Long getId();
    Long getPropertyId();
    String getPropertyName();
    BigDecimal getPropertyPrice();
    String getPropertyDescription();
    LocalDate getStartDate();
    LocalDate getEndDate();
    String getDuration();
}
