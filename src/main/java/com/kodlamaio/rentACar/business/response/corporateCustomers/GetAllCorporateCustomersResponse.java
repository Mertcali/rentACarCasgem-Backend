package com.kodlamaio.rentACar.business.response.corporateCustomers;

import com.kodlamaio.rentACar.business.response.customers.GetAllCustomersResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCorporateCustomersResponse {
	private int id;
	private String taxNumber;
	private int customerId;
	private String customerNumber;
	private Integer findexScore;
	private String userEmail;
}
