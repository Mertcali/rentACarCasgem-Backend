package com.kodlamaio.rentACar.business.response.colors;

import com.kodlamaio.rentACar.business.response.cars.GetAllCarsResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllColorsResponse {
	private int id;
	private String name;
}
