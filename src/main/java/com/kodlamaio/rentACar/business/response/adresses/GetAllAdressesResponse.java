package com.kodlamaio.rentACar.business.response.adresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllAdressesResponse {
	private int id;
	private String contactAddress;
	private String invoiceAddress;
	private int userId;
}
