package com.kodlamaio.rentACar.business.response.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerResponse {
	

	private String customerNumber;
	private int id;
	private int customerId;

}
