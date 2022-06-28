package com.kodlamaio.rentACar.business.request.invoices.forIndividualCustomers;

import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteInvoiceForIndividualCustomerRequest {

	private int id;
}
