package com.kodlamaio.rentACar.business.response.invoices;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetInvoiceResponse {
	
	private int id;
	private String invoiceNumber;
	private double totalPrice;
	private int state;
	private Date pickupDate;
	private Date returnDate;
	private int totalDays;
	private int totalDaysAdditionalItem;
	private int pickUpCityId;
	private int returnCityId;
	private int customerId;
}
