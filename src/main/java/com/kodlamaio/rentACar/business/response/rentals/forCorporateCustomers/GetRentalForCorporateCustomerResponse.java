package com.kodlamaio.rentACar.business.response.rentals.forCorporateCustomers;

import java.util.Date;

import com.kodlamaio.rentACar.business.response.rentals.GetAllRentalsResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRentalForCorporateCustomerResponse {

	private int id;
	private Date pickupDate;
	private Date returnDate;
	private int totalDays;
	private double totalPrice;
	private int carId;
	private int corporateCustomerId;
	private int orderedAdditionalItemId;
	private int pickUpCityId;
	private int returnCityId;
}
