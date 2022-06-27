package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CustomerService;
import com.kodlamaio.rentACar.business.request.customers.CreateCustomerRequest;
import com.kodlamaio.rentACar.business.request.customers.DeleteCustomerRequest;
import com.kodlamaio.rentACar.business.request.customers.UpdateCustomerRequest;
import com.kodlamaio.rentACar.business.response.customers.GetAllCustomersResponse;
import com.kodlamaio.rentACar.business.response.customers.GetCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CorporateCustomerRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CustomerRepository;
import com.kodlamaio.rentACar.entities.concretes.Customer;

@Service
public class CustomerManager implements CustomerService{

	private ModelMapperService modelMapperService;
	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerManager(ModelMapperService modelMapperService, CustomerRepository customerRepository) {
		super();
		this.modelMapperService = modelMapperService;
		this.customerRepository = customerRepository;
	}

	@Override
	public Result add(CreateCustomerRequest createCustomerRequest) {
		Customer customer = this.modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
		customerRepository.save(customer);
		return new SuccessResult("CUSTOMER_ADDED");
	}

	@Override
	public Result update(UpdateCustomerRequest updateCustomerRequest) {
		Customer customer = this.modelMapperService.forRequest().map(updateCustomerRequest, Customer.class);
		customerRepository.save(customer);
		return new SuccessResult("CUSTOMER_UPDATED");
	}

	@Override
	public Result delete(DeleteCustomerRequest deleteCustomerRequest) {
		customerRepository.deleteById(deleteCustomerRequest.getId());
		return new SuccessResult("CUSTOMER_DELETED");
	}

	@Override
	public DataResult<GetCustomerResponse> getById(GetCustomerResponse getCustomerResponse) {
		Customer customer = customerRepository.findById(getCustomerResponse.getId());
		GetCustomerResponse response = this.modelMapperService.forResponse().map(customer, GetCustomerResponse.class);
		return new SuccessDataResult<GetCustomerResponse>(response,"CUSTOMER_LISTED");
	}

	@Override
	public DataResult<List<GetAllCustomersResponse>> getAll() {
		List<Customer> customers = customerRepository.findAll();
		List<GetAllCustomersResponse> response = customers.stream().map(customer -> this.modelMapperService.forResponse()
				.map(customer, GetAllCustomersResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCustomersResponse>>(response,"ALL_CUSTOMERS_LISTED");
	}

}
