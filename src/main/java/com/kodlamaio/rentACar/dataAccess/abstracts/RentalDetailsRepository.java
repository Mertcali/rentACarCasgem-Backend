package com.kodlamaio.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kodlamaio.rentACar.entities.concretes.Rental;
import com.kodlamaio.rentACar.entities.dtos.RentalDetails;

public interface RentalDetailsRepository extends JpaRepository<Rental, Integer>{
	
	@Query(value = "Select new com.kodlamaio.rentACar.entities.dtos.RentalDetails(c.description,c.dailyPrice, "
			+  "r.pickupDate, r.returnedDate, r.totalPrice,"
			+ " r.pickUpCity, r.returnCity) "
			+ "From Car c "
			+ "Inner Join c.rentals r ", nativeQuery = true	)
	List<RentalDetails> callRentalDetails(); 
	
}
