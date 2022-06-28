package com.kodlamaio.rentACar.business.request.invoices;

import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {
	
	private int id;

	@Size(min=8,max=10)
	private String invoiceNumber;

	private int rentalId;
	private double totalPrice;
}
