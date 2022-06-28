package com.kodlamaio.rentACar.business.request.rentals.forIndividualCustomers;

import java.util.Date;

import com.kodlamaio.rentACar.business.request.rentals.DeleteRentalRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalForIndividualRequest {
	
	private int id;
	
	private Date pickupDate;

	private Date returnDate;

	private int carId;
	
	private int individualCustomerId;
	
	private int pickUpCityId;

	private int returnCityId;
	
	private int orderedAdditionalItemId;
}
