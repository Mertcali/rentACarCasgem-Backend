package com.kodlamaio.rentACar.business.request.invoices.forIndividualCustomers;

import javax.validation.constraints.Size;

import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceForIndividualCustomerRequest {
	private int id;

	@Size(min=8,max=10)
	private String invoiceNumber;

	private int rentalId;
	private double totalPrice;
}
