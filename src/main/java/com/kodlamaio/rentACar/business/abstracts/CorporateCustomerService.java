package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.corporateCustomers.CreateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.request.corporateCustomers.DeleteCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.request.corporateCustomers.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.response.corporateCustomers.GetAllCorporateCustomersResponse;
import com.kodlamaio.rentACar.business.response.corporateCustomers.GetCorporateCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface CorporateCustomerService {

	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);

	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);

	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);

	DataResult<GetCorporateCustomerResponse> getById(GetCorporateCustomerResponse getCorporateCustomerResponse);

	DataResult<List<GetAllCorporateCustomersResponse>> getAll();
}
