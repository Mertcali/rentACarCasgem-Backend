package com.kodlamaio.rentACar.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.OrderedAdditionalItemService;
import com.kodlamaio.rentACar.business.request.orderedAdditionalItems.CreateOrderedAdditionalItemRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/orderedadditionalitems")
public class OrderedAdditionalItemsController {
	@Autowired
	private OrderedAdditionalItemService orderedAdditionalItemService;

	@PostMapping("/add")
	public Result add(@RequestBody CreateOrderedAdditionalItemRequest createOrderedAdditionalItemRequest) {
		return this.orderedAdditionalItemService.add(createOrderedAdditionalItemRequest);
	}

}
