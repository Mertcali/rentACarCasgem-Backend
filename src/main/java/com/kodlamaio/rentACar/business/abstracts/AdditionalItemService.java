package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.additionalItems.CreateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.request.additionalItems.DeleteAdditionalItemRequest;
import com.kodlamaio.rentACar.business.request.additionalItems.UpdateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.response.additionalItems.GetAdditionalItemResponse;
import com.kodlamaio.rentACar.business.response.additionalItems.GetAllAdditionalItemsResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AdditionalItemService {
	
	Result add(CreateAdditionalItemRequest createAdditionalItemRequest);

	Result update(UpdateAdditionalItemRequest updateAdditionalItemRequest);

	Result delete(DeleteAdditionalItemRequest deleteAdditionalItemRequest);

	DataResult<GetAdditionalItemResponse> getById(GetAdditionalItemResponse getAdditionalItemResponse);

	DataResult<List<GetAllAdditionalItemsResponse>> getAll();
}
