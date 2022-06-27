package com.kodlamaio.rentACar.business.request.customers;

import com.kodlamaio.rentACar.business.request.additionalItems.CreateAdditionalItemRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCustomerRequest {

	private int id;
}
