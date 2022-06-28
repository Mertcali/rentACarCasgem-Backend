package com.kodlamaio.rentACar.business.response.invoices;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllInvoicesResponse {
	private int id;
	private String invoiceNumber;
	private double totalPrice;
	private int state;
	private Date pickupDate;
	private Date returnDate;
	private int totalDaysAdditionalItem;
	private int totalDaysRental;
	private int pickUpCityId;
	private int returnCityId;
	private int customerId;
}
