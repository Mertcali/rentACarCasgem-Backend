package com.kodlamaio.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.OrderedAdditionalItemService;
import com.kodlamaio.rentACar.business.request.orderedAdditionalItems.CreateOrderedAdditionalItemRequest;
import com.kodlamaio.rentACar.business.response.additionalItems.GetAdditionalItemResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.OrderedAdditionalItemRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.OrderedAdditionalItem;
import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;
@Service
public class OrderedAdditionalItemManager implements OrderedAdditionalItemService {
	
	private OrderedAdditionalItemRepository orderedAdditionalItemRepository;
	private AdditionalItemRepository additionalItemRepository;
	private RentalRepository rentalRepository;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public OrderedAdditionalItemManager(OrderedAdditionalItemRepository orderedAdditionalItemRepository,
			AdditionalItemRepository additionalItemRepository, RentalRepository rentalRepository,
			ModelMapperService modelMapperService) {
		super();
		this.orderedAdditionalItemRepository = orderedAdditionalItemRepository;
		this.additionalItemRepository = additionalItemRepository;
		this.rentalRepository = rentalRepository;
		this.modelMapperService = modelMapperService;
	}



	@Override
	public Result add(CreateOrderedAdditionalItemRequest createOrderedAdditionalItemRequest) {
		OrderedAdditionalItem orderedAdditionalItem = this.modelMapperService.forRequest().map(createOrderedAdditionalItemRequest, OrderedAdditionalItem.class);
		AdditionalItem additionalItem=this.additionalItemRepository.findById(createOrderedAdditionalItemRequest.getAdditionalItemId());
		int days=orderedAdditionalItem.getTotalDaysAdditionalItem();
		double totalPrice=additionalItem.getDailyPrice()*days;
		orderedAdditionalItem.setTotalPrice(totalPrice);		
		orderedAdditionalItem.setAdditionalItem(additionalItem);
		this.orderedAdditionalItemRepository.save(orderedAdditionalItem);
		return new SuccessResult("ORDERED_ADDITIONAL_ITEM_ADDED");
	}

}
