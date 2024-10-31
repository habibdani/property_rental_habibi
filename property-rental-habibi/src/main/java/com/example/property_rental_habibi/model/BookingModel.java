package com.example.property_rental_habibi.model;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@Entity
@Table(name = "booking")
public class BookingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_id")
    private Long id;

    @Column(name = "b_p_id", nullable = false)
    private Long propertyId;

    @Column(name = "b_start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "b_end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "b_duration", nullable = false)
    private int duration;

    @Column(name = "b_stato", columnDefinition = "ENUM('BOOKED', 'CANCELLED', 'COMPLETED') DEFAULT 'BOOKED'")
    private String stato = "BOOKED";

    @Column(name = "b_status")
    private Boolean status = true;

    @PrePersist
    @PreUpdate
    public void calculateDuration() {
        this.duration = (int) ChronoUnit.DAYS.between(startDate, endDate);
    }
}
