package com.kodlamaio.rentACar.business.response.users;

import com.kodlamaio.rentACar.business.response.cars.ReadCarResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadUserResponse {
	
	private String nationalIdentity;

}
