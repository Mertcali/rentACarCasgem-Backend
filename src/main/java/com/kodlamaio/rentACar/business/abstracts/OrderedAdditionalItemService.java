package com.kodlamaio.rentACar.business.abstracts;

import com.kodlamaio.rentACar.business.request.orderedAdditionalItems.CreateOrderedAdditionalItemRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface OrderedAdditionalItemService {
	
	Result add(CreateOrderedAdditionalItemRequest createOrderedAdditionalItemRequest);

}
