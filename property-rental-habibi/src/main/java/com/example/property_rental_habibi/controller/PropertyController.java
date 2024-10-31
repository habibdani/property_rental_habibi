package com.example.property_rental_habibi.controller;

import com.example.property_rental_habibi.dto.UpdatePropertyRequest;
import com.example.property_rental_habibi.model.PropertyModel;
import com.example.property_rental_habibi.service.PropertyService;
import com.example.property_rental_habibi.response.ApiResponse;
import com.example.property_rental_habibi.response.PaginationResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAllProperties(
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "10") int size) {

        int currentPage = (page == null || page < 1) ? 0 : page - 1;

        Page<PropertyModel> propertyPage = propertyService.getAllActiveProperties(PageRequest.of(currentPage, size));

        Map<String, Object> response = new HashMap<>();
        response.put("property", propertyPage.getContent());
        response.put("pagination", new PaginationResponse(
                propertyPage.getNumber() + 1,
                propertyPage.getTotalPages(),
                propertyPage.getTotalElements(),
                propertyPage.getSize()
        ));

        return ResponseEntity.ok(new ApiResponse<>("success", "Data fetched successfully", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getPropertyById(@PathVariable Long id) {
        PropertyModel property = propertyService.getPropertyById(id);

        if (property != null && property.getStatus()) {
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("property", property);
            return ResponseEntity.ok(new ApiResponse<>("success", "Data fetched successfully", responseData));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body(new ApiResponse<>("fail", "Resource not found", null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> addProperty(@Valid @RequestBody PropertyModel property, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("error", "Invalid request data", errors));
        }

        PropertyModel createdProperty = propertyService.addProperty(property);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("success", "Property created successfully", createdProperty));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateProperty(
        @PathVariable Long id, 
        @Valid @RequestBody UpdatePropertyRequest updateRequest, 
        BindingResult bindingResult) {
    
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("error", "Invalid request data", errors));
        }

        PropertyModel existingProperty = propertyService.getPropertyById(id);
        if (existingProperty == null || !existingProperty.getStatus()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("fail", "Resource not found", null));
        }

        if (updateRequest.getName() != null) {
            existingProperty.setName(updateRequest.getName());
        }
        if (updateRequest.getLocation() != null) {
            existingProperty.setLocation(updateRequest.getLocation());
        }
        if (updateRequest.getPrice() != null) {
            existingProperty.setPrice(updateRequest.getPrice());
        }
        if (updateRequest.getDescription() != null) {
            existingProperty.setDescription(updateRequest.getDescription());
        }

        PropertyModel updatedProperty = propertyService.updateProperty(id, existingProperty);
        return ResponseEntity.ok(new ApiResponse<>("success", "Property updated successfully", updatedProperty));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProperty(@PathVariable Long id) {
        PropertyModel property = propertyService.getPropertyById(id);
    
        if (property == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>("fail", "Resource not found", null));
        }
    
        if (!property.getStatus()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>("fail", "Property has already been marked as inactive", null));
        }
    
        propertyService.deleteProperty(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Property status updated to inactive", null));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleServerError(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ApiResponse<>("error", "An unexpected error occurred. Please try again later.", null));
    }
}
