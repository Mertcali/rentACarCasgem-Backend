package com.kodlamaio.rentACar.business.request.users;

import javax.validation.constraints.Email;
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
	

	private String userPassword;

	@Size(min=4)
	private int birthYear;
	@Email
	private String userEmail;

}
