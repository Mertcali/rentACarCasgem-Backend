package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.CustomerService;
import com.kodlamaio.rentACar.business.request.customers.CreateCustomerRequest;
import com.kodlamaio.rentACar.business.request.customers.DeleteCustomerRequest;
import com.kodlamaio.rentACar.business.request.customers.UpdateCustomerRequest;
import com.kodlamaio.rentACar.business.response.customers.GetAllCustomersResponse;
import com.kodlamaio.rentACar.business.response.customers.GetCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateCustomerRequest createCustomerRequest) {
		return this.customerService.add(createCustomerRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateCustomerRequest updateCustomerRequest) {
		return this.customerService.update(updateCustomerRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteCustomerRequest deleteCustomerRequest) {
		return this.customerService.delete(deleteCustomerRequest);
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetCustomerResponse> getById(@RequestBody GetCustomerResponse getCustomerResponse){
		return this.customerService.getById(getCustomerResponse);
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllCustomersResponse>> getAll(){
		return this.customerService.getAll();
	}
	
}
