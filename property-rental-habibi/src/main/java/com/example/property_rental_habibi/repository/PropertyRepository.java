package com.example.property_rental_habibi.repository;

import com.example.property_rental_habibi.model.PropertyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyModel, Long> {
    Page<PropertyModel> findByStatusTrue(Pageable pageable);
}
