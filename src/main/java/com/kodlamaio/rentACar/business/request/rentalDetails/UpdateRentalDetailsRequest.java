package com.kodlamaio.rentACar.business.request.rentalDetails;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalDetailsRequest {
	
	private int id;

	private int rentalId;
	private int orderedAdditionalItemId;

}
