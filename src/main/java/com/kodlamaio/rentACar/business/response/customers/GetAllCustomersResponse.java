package com.kodlamaio.rentACar.business.response.customers;

import com.kodlamaio.rentACar.business.response.additionalItems.GetAdditionalItemResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCustomersResponse {

	private String customerNumber;
	private int id;
	private int customerId;

}
