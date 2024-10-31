package com.example.property_rental_habibi.controller;

import com.example.property_rental_habibi.model.BookingModel;
import com.example.property_rental_habibi.response.ApiResponse;
import com.example.property_rental_habibi.service.BookingService;
import com.example.property_rental_habibi.response.PaginationResponse;
import com.example.property_rental_habibi.model.BookingDetailProjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<ApiResponse<BookingModel>> createBooking(@RequestBody BookingModel booking) {
        BookingModel createdBooking = bookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("success", "Booking created successfully", createdBooking));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAllBookings(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") int size) {

        int currentPage = (page == null || page < 1) ? 0 : page - 1;
        Pageable pageable = PageRequest.of(currentPage, size);
        Page<BookingModel> bookingPage = bookingService.getAllBookings(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("bookings", bookingPage.getContent());
        response.put("pagination", new PaginationResponse(
                bookingPage.getNumber() + 1,
                bookingPage.getTotalPages(),
                bookingPage.getTotalElements(),
                bookingPage.getSize()
        ));

        return ResponseEntity.ok(new ApiResponse<>("success", "Data fetched successfully", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getBookingById(@PathVariable Long id) {
        Optional<BookingDetailProjection> bookingDetail = bookingService.getBookingDetailsById(id);

        if (bookingDetail.isPresent()) {
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("booking", bookingDetail.get());
            return ResponseEntity.ok(new ApiResponse<>("success", "Data fetched successfully", responseData));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>("fail", "Booking not found", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookingModel>> updateBooking(@PathVariable Long id, @RequestBody BookingModel updatedBooking) {
        BookingModel booking = bookingService.updateBooking(id, updatedBooking);
        if (booking != null) {
            return ResponseEntity.ok(new ApiResponse<>("success", "Booking updated successfully", booking));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("fail", "Booking not found", null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBooking(@PathVariable Long id) {
        boolean isDeleted = bookingService.deleteBooking(id);
        if (isDeleted) {
            return ResponseEntity.ok(new ApiResponse<>("success", "Booking status updated to inactive", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("fail", "Booking not found", null));
        }
    }
}
