package com.kodlamaio.rentACar.business.request.invoices;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {
	
	@Size(min=7,max=8)
	private String invoiceNumber;
	private int rentalDetailId;

}
