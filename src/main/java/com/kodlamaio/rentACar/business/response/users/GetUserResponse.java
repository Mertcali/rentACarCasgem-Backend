package com.kodlamaio.rentACar.business.response.users;

import com.kodlamaio.rentACar.business.response.cars.GetCarResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponse {
	
	private int id;
	private String userFirstName;
	private String userLastName;
	private String userNationalIdentity;
	private String userPassword;

}
