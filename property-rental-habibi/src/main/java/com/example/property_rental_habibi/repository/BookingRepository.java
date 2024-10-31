package com.example.property_rental_habibi.repository;

import com.example.property_rental_habibi.model.BookingDetailProjection;
import com.example.property_rental_habibi.model.BookingModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingModel, Long> {

    @Query(value = """
        SELECT
            b.b_id AS id,
            b.b_p_id AS propertyId,
            p.p_name AS propertyName,
            p.p_price AS propertyPrice,
            p.p_description AS propertyDescription,
            b.b_start_date AS startDate,
            b.b_end_date AS endDate,
            CONCAT(b.b_duration, ' day') AS duration
        FROM
            booking b
            JOIN property p ON b.b_p_id = p.p_id
        WHERE
            p.p_status = TRUE
            AND b.b_status = TRUE
            AND b.b_id = :id
        """, nativeQuery = true)
    Optional<BookingDetailProjection> findBookingDetailsById(@Param("id") Long id);
}
