package com.kodlamaio.rentACar.business.request.adresses;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAdressRequest {
	private int id;
	
	private String contactAddress;
	private String invoiceAddress;
	private int customerId;
}
