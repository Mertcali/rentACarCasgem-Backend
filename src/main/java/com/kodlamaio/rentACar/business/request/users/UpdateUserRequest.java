package com.kodlamaio.rentACar.business.request.users;

import com.kodlamaio.rentACar.business.request.cars.UpdateCarRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
	
	private int id;
	private String userFirstName;
	private String userLastName;
	private String userNationalIdentity;
	private String userPassword;

}
