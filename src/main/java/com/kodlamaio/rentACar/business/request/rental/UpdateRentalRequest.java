package com.kodlamaio.rentACar.business.request.rental;

import java.time.LocalDate;
import java.util.Date;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {

	private int id;

	private Date pickupDate;

	private Date returnDate;

	private int totalDays;

	private int carId;

	private int pickUpCityId;

	private int returnCityId;

	private int customerId;
	
	private int orderedAdditionalItemId;
}
