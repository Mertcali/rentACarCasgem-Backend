package com.kodlamaio.rentACar.business.request.users;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.kodlamaio.rentACar.business.request.cars.UpdateCarRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
	
	private int id;
	private String userPassword;

	@Size(min=4)
	private int birthYear;
	@Email
	private String userEmail;

}
