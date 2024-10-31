package com.example.property_rental_habibi.service;

import com.example.property_rental_habibi.model.PropertyModel;
import com.example.property_rental_habibi.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public Page<PropertyModel> getAllActiveProperties(Pageable pageable) {
      return propertyRepository.findByStatusTrue(pageable);
    }

    public PropertyModel getPropertyById(Long id) {
      return propertyRepository.findById(id).orElse(null);
    }

    public PropertyModel addProperty(PropertyModel property) {
      return propertyRepository.save(property);
    }

    public PropertyModel updateProperty(Long id, PropertyModel propertyDetails) {
      PropertyModel property = propertyRepository.findById(id).orElse(null);
      if (property != null) {
          property.setName(propertyDetails.getName());
          property.setLocation(propertyDetails.getLocation());
          property.setPrice(propertyDetails.getPrice());
          property.setDescription(propertyDetails.getDescription());
          property.setStatus(propertyDetails.getStatus());
          return propertyRepository.save(property);
      }
      return null;
    }

    public boolean deleteProperty(Long id) {
      PropertyModel property = propertyRepository.findById(id).orElse(null);
      if (property != null) {
          property.setStatus(false);
          propertyRepository.save(property);
          return true;
      }
      return false;
    }
}
