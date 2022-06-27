package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.customers.CreateCustomerRequest;
import com.kodlamaio.rentACar.business.request.customers.DeleteCustomerRequest;
import com.kodlamaio.rentACar.business.request.customers.UpdateCustomerRequest;
import com.kodlamaio.rentACar.business.response.customers.GetAllCustomersResponse;
import com.kodlamaio.rentACar.business.response.customers.GetCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface CustomerService {

	Result add(CreateCustomerRequest createCustomerRequest);

	Result update(UpdateCustomerRequest updateCustomerRequest);

	Result delete(DeleteCustomerRequest deleteCustomerRequest);

	DataResult<GetCustomerResponse> getById(GetCustomerResponse getCustomerResponse);

	DataResult<List<GetAllCustomersResponse>> getAll();
}
