package com.kodlamaio.rentACar.business.request.maintenance;

import java.time.LocalDate;
import java.util.Date;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaintenanceRequest {
	

	private Date sentDate;

	private Date returnedDate;

	private int carId;
	

}
