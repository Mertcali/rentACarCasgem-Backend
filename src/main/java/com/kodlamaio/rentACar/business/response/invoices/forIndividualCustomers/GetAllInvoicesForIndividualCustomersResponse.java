package com.kodlamaio.rentACar.business.response.invoices.forIndividualCustomers;

import java.util.Date;

import com.kodlamaio.rentACar.business.response.invoices.GetInvoiceResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllInvoicesForIndividualCustomersResponse {
	private int id;
	private String invoiceNumber;
	private double totalPrice;
	private int state;
	private Date pickupDate;
	private Date returnDate;
	private int totalDays;
	private int pickUpCityId;
	private int returnCityId;
	private int individualCustomerId;
}
