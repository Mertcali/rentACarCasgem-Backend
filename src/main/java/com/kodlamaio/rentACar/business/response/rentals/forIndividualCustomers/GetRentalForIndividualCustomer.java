package com.kodlamaio.rentACar.business.response.rentals.forIndividualCustomers;

import java.util.Date;

import com.kodlamaio.rentACar.business.response.rentals.GetAllRentalsResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRentalForIndividualCustomer {
	private int id;
	private Date pickupDate;
	private Date returnDate;
	private int totalDays;
	private double totalPrice;
	private int carId;
	private int individualCustomerId;
	private int orderedAdditionalItemId;
	private int pickUpCityId;
	private int returnCityId;
}
