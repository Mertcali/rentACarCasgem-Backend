package com.kodlamaio.rentACar.business.request.rentals;

import java.time.LocalDate;
import java.util.Date;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class CreateRentalRequest {

	private Date pickupDate;

	private Date returnDate;

	private int carId;
	
	private int customerId;
	
	private int pickUpCityId;

	private int returnCityId;
	
	private int orderedAdditionalItemId;

}
