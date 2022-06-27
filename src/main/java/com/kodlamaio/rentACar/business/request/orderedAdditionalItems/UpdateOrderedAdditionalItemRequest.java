package com.kodlamaio.rentACar.business.request.orderedAdditionalItems;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderedAdditionalItemRequest {
	private int id;

	private int additionalItemId;
}
