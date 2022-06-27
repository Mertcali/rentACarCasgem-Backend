package com.kodlamaio.rentACar.business.response.maintenances;

import java.util.Date;

import com.kodlamaio.rentACar.business.response.cars.GetAllCarsResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllMaintenancesResponse {

	private int id;
	private Date dateSent;
	private Date dateReturned;
	
}
