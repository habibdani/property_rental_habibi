package com.example.property_rental_habibi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "property")
public class PropertyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private Long id;

    @NotEmpty(message = "The p_name field is required")
    @Size(max = 100, message = "The p_name must be less than or equal to 100 characters")
    @Column(name = "p_name", nullable = false, length = 100)
    private String name;

    @NotEmpty(message = "The p_location field is required")
    @Size(max = 255, message = "The p_location must be less than or equal to 255 characters")
    @Column(name = "p_location", length = 255)
    private String location;

    @NotNull(message = "The p_price field is required")
    @Column(name = "p_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal price;
  
    @NotEmpty(message = "The p_description field is required")
    @Column(name = "p_description", columnDefinition = "TEXT")
    private String description;
  
    // @JsonIgnore
    @NotNull(message = "The p_status field is required")
    @Column(name = "p_status")
    private Boolean status;
  
    @JsonIgnore
    @Column(name = "p_created_at", updatable = false)
    private LocalDateTime createdAt;
  
    @Column(name = "p_updated_at")
    private LocalDateTime updatedAt;
  
    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now(ZoneId.of("Asia/Jakarta"));
    }
  
    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now(ZoneId.of("Asia/Jakarta"));
    }
}
