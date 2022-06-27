package com.kodlamaio.rentACar.business.response.corporateCustomers;

import com.kodlamaio.rentACar.business.request.additionalItems.CreateAdditionalItemRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCorporateCustomerResponse {

	private int id;
	private String taxNumber;
	private int customerId;
}
