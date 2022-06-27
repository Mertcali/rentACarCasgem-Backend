package com.kodlamaio.rentACar.business.response.orderedAdditionalItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderedAdditionalItemResponse {
	private int id;
	private double totalPrice;
	private int totalDays;
	private int additionalItemId;
}
