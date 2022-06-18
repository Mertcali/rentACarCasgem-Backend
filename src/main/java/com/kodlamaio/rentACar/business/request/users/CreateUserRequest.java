package com.kodlamaio.rentACar.business.request.users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.kodlamaio.rentACar.business.request.cars.CreateCarRequest;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
	
	
	private int id;
	
	@NotBlank
	@NotNull
	@Size(min=2)
	private String userFirstName;
	
	@NotBlank
	@NotNull
	@Size(min=2)
	private String userLastName;
	
	@NotNull
	@NotBlank
	@Size(min=11,max=11,message ="Tc No:11 Karakterden oluşmalı")
	private String userNationalIdentity;
	
	@NotNull
	@NotBlank
	private String userPassword;
	
	private int birthYear;

}
