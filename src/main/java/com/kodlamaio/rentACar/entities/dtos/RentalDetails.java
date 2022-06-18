package com.kodlamaio.rentACar.entities.dtos;

import java.time.LocalDate;

import com.kodlamaio.rentACar.entities.concretes.City;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "rental_details")
public class RentalDetails {
	
	// @Id()
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name="id")
	private int id;
	
	//@Column(name="description")
	private String description;
	
	//@Column(name="dailyPrice")
	private double dailyPrice;
	
	//@Column(name="brand_name")
	private String brandName;
	
	//@Column(name="color_name")
	private String colorName;
	
	//@Column(name="first_name")
	private String firstName;
	
	//@Column(name="last_name")
	private String lastName;
	
	//@Column(name="plate")
	private String plate;
	
	//@Column(name="kilometer")
	private Integer kilometer;
	
	//@Column(name="pickup_date")
	private LocalDate pickupDate;
	
	//@Column(name="returned_date")
	private LocalDate returnedDate;
	
	//@Column(name="total_price")
	private double totalPrice;
	
	//@Column(name="pickup_city")
	private City pickUpCity;
	
	//@Column(name="return_city")
	private City returnCity;

}
