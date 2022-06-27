package com.kodlamaio.rentACar.business.response.orderedAdditionalItems;

import com.kodlamaio.rentACar.business.response.additionalItems.GetAllAdditionalItemsResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrderedAdditionalItemsResponse {
	private int id;
	private double totalPrice;
	private int totalDays;
	private int additionalItemId;
}
