package com.kodlamaio.rentACar.business.request.individualCustomers;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.kodlamaio.rentACar.business.request.customers.CreateCustomerRequest;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateIndividualCustomerRequest {

	private int id;

	@Size(min=2)
	private String firstName;

	@Size(min=2)
	private String lastName;

	@Size(min=11,max=11,message ="Tc No:11 Karakterden oluşmalı")
	private String nationalIdentity;

	@Min(value=4)
	private Integer birthYear;

	private String userPassword;
	@Email
	private String userEmail;
	
	private Integer findexScore;
}
