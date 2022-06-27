package com.kodlamaio.rentACar.business.request.adresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceAdressRequest {
	private String invoiceAddress;
	private int customerId;
}
