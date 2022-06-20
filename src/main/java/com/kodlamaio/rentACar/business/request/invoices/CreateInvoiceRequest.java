package com.kodlamaio.rentACar.business.request.invoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {
	
	private int id;
	private int invoiceNumber;
	private int rentalDetailsId;
	private double totalPrice;

}
