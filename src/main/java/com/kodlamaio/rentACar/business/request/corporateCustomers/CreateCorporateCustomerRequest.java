package com.kodlamaio.rentACar.business.request.corporateCustomers;

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
public class CreateCorporateCustomerRequest {


	@Size(min=10,max=10)
	private String taxNumber;
	
	private String userPassword;
	@Email
	private String userEmail;
	
}
