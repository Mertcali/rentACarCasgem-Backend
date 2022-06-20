package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entities.concretes.Rental;
import com.kodlamaio.rentACar.entities.concretes.RentalDetails;

public interface RentalDetailsRepository extends JpaRepository<RentalDetails, Integer>{
	
	/* @Query(value = "Select new com.kodlamaio.rentACar.entities.dtos.RentalDetails(c.description,c.dailyPrice, "
			+  "r.pickupDate, r.returnedDate, r.totalPrice,"
			+ " r.pickUpCity, r.returnCity) "
			+ "From Car c "
			+ "Inner Join c.rentals r ", nativeQuery = true	)
	List<com.kodlamaio.rentACar.entities.concretes.RentalDetails> callRentalDetails(); */
	
}
