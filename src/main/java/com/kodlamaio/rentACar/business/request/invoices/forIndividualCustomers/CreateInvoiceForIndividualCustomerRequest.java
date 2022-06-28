package com.kodlamaio.rentACar.business.request.invoices.forIndividualCustomers;

import javax.validation.constraints.Size;

import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceForIndividualCustomerRequest {

	@Size(min=7,max=8)
	private String invoiceNumber;
	private int rentalId;
}
