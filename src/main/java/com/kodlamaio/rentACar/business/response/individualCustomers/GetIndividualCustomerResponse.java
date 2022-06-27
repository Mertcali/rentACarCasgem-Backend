package com.kodlamaio.rentACar.business.response.individualCustomers;

import com.kodlamaio.rentACar.business.response.customers.GetAllCustomersResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetIndividualCustomerResponse {

	private int individualCustomerId;
	private String firstName;
	private String lastName;
	private Integer birthYear;
}
