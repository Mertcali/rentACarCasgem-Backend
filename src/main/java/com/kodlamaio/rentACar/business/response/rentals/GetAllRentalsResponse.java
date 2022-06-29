package com.kodlamaio.rentACar.business.response.rentals;

import java.util.Date;

import com.kodlamaio.rentACar.business.response.cars.GetAllCarsResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllRentalsResponse {

	private int id;
	private Date pickupDate;
	private Date returnDate;
	private int totalDays;
	private double totalPrice;
	private int carId;
	private int customerId;
	private int individualCustomerId;
	private int corporateCustomerId;
	private int orderedAdditionalItemId;
	private int pickUpCityId;
	private int returnCityId;
	
	
}
