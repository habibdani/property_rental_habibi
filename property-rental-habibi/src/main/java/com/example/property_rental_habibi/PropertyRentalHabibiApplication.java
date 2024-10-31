package com.example.property_rental_habibi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.property_rental_habibi")
public class PropertyRentalHabibiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyRentalHabibiApplication.class, args);
	}

}
