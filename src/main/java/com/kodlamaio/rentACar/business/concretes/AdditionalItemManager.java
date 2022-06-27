package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalItemService;
import com.kodlamaio.rentACar.business.request.additionalItems.CreateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.request.additionalItems.DeleteAdditionalItemRequest;
import com.kodlamaio.rentACar.business.request.additionalItems.UpdateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.response.additionalItems.GetAdditionalItemResponse;
import com.kodlamaio.rentACar.business.response.additionalItems.GetAllAdditionalItemsResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;

@Service
public class AdditionalItemManager implements AdditionalItemService {

	
	private AdditionalItemRepository additionalItemRepository;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public AdditionalItemManager(AdditionalItemRepository additionalItemRepository,
			ModelMapperService modelMapperService) {
		super();
		this.additionalItemRepository = additionalItemRepository;
		this.modelMapperService = modelMapperService;
	}

	
	@Override
	public Result add(CreateAdditionalItemRequest createAdditionalItemRequest) {
	
		checkIfAdditionalItemExistsByName(createAdditionalItemRequest.getName());
		AdditionalItem additionalItem=this.modelMapperService.forRequest().map(createAdditionalItemRequest, AdditionalItem.class);
		this.additionalItemRepository.save(additionalItem);
		return new SuccessResult("ADDITIONAL_ITEM_ADDED");
	}

	@Override
	public Result update( UpdateAdditionalItemRequest updateAdditionalItemRequest) {
		AdditionalItem additionalItem=this.modelMapperService.forRequest().map(updateAdditionalItemRequest, AdditionalItem.class);
		this.additionalItemRepository.save(additionalItem);
		return new SuccessResult("ADDITIONAL_ITEM_UPDATED");
		
	}

	@Override
	public Result delete(DeleteAdditionalItemRequest deleteAdditionalItemRequest) {
		this.additionalItemRepository.deleteById(deleteAdditionalItemRequest.getId());
		return new SuccessResult("ADDITIONAL_ITEM_DELETED");
	}

	@Override
	public DataResult<GetAdditionalItemResponse> getById(GetAdditionalItemResponse getAdditionalItemResponse) {
		AdditionalItem additionalItem = this.additionalItemRepository.findById(getAdditionalItemResponse.getId());
		GetAdditionalItemResponse response = this.modelMapperService.forResponse().map(additionalItem, GetAdditionalItemResponse.class);
		return new SuccessDataResult<GetAdditionalItemResponse>(response,"LISTING_ADDITIONAL_ITEM_SUCCESS");
		
	}

	@Override
	public DataResult<List<GetAllAdditionalItemsResponse>>getAll() {
		List<AdditionalItem> additionalItems=this.additionalItemRepository.findAll();
		List<GetAllAdditionalItemsResponse>response=additionalItems.stream()
				.map(additionalItem -> this.modelMapperService.forResponse().map(additionalItem, GetAllAdditionalItemsResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAdditionalItemsResponse>>(response,"LISTING_ALL_ADDITIONAL_ITEMS_SUCCESS");
	}

	private void checkIfAdditionalItemExistsByName(String name) {
		AdditionalItem additionalItem = additionalItemRepository.findByName(name);
		if(additionalItem!=null) {
			throw new BusinessException("ADDITIONAL_ITEM_EXISTS");
		}
		
	}


}
