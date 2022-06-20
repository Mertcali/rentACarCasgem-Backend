package com.kodlamaio.rentACar.business.response.rentalDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllRentalDetailsResponse {

	private int id;
	private double totalPrice;
	private int rentalId;
	private int additionalId;
}
