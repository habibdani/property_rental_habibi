package com.example.property_rental_habibi.service;

import com.example.property_rental_habibi.model.BookingDetailProjection;
import com.example.property_rental_habibi.model.BookingModel;
import com.example.property_rental_habibi.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public BookingModel createBooking(BookingModel booking) {
        booking.calculateDuration();
        return bookingRepository.save(booking);
    }

    public Optional<BookingDetailProjection> getBookingDetailsById(Long id) {
      return bookingRepository.findBookingDetailsById(id);
    }

    public Page<BookingModel> getAllBookings(Pageable pageable) {
      return bookingRepository.findAll(pageable);
    }

    public List<BookingModel> getAllBookings() {
        return bookingRepository.findAll();
    }

    public BookingModel updateBooking(Long id, BookingModel updatedBooking) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setPropertyId(updatedBooking.getPropertyId());
            booking.setStartDate(updatedBooking.getStartDate());
            booking.setEndDate(updatedBooking.getEndDate());
            booking.calculateDuration();
            booking.setStato(updatedBooking.getStato());
            return bookingRepository.save(booking);
        }).orElse(null);
    }

    public boolean deleteBooking(Long id) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setStatus(false);
            bookingRepository.save(booking);
            return true;
        }).orElse(false);
    }
}
