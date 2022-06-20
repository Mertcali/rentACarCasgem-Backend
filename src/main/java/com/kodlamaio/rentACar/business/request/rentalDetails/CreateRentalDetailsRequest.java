package com.kodlamaio.rentACar.business.request.rentalDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalDetailsRequest {
	
	private int id;
	private double totalPrice;
	private int rentalId;
	private int additionalId;

}
