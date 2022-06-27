package com.kodlamaio.rentACar.business.request.customers;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.kodlamaio.rentACar.business.request.additionalItems.CreateAdditionalItemRequest;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerRequest {

	private int id;

	@Size(min=12,max=12)
	private String customerNumber;
	private Integer findexScore;
	
	private String userPassword;
	@Email
	private String userEmail;
}
