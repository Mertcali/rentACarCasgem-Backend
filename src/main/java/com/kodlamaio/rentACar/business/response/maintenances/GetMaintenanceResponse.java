package com.kodlamaio.rentACar.business.response.maintenances;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMaintenanceResponse {
	private int id;
	private Date dateSent;
	private Date dateReturned;
}
