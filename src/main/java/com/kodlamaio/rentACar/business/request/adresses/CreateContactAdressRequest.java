package com.kodlamaio.rentACar.business.request.adresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateContactAdressRequest {

	private String contactAddress;
	private int customerId;
}
