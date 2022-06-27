package com.kodlamaio.rentACar.api.controllers;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerService;
import com.kodlamaio.rentACar.business.request.individualCustomers.CreateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.request.individualCustomers.DeleteIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.request.individualCustomers.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.response.individualCustomers.GetAllIndividualCustomersResponse;
import com.kodlamaio.rentACar.business.response.individualCustomers.GetIndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/individualcustomers")
public class IndividualCustomersController {
	
	@Autowired
	private IndividualCustomerService individualCustomerService;
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NumberFormatException, RemoteException {
		return this.individualCustomerService.add(createIndividualCustomerRequest);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteIndividualCustomerRequest deleteIndividualCustomerRequest)
	{
		return this.individualCustomerService.delete(deleteIndividualCustomerRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws NumberFormatException, RemoteException {
		return this.individualCustomerService.update(updateIndividualCustomerRequest);
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetIndividualCustomerResponse> getById(@RequestBody GetIndividualCustomerResponse getIndividualCustomerResponse){
		return this.individualCustomerService.getById(getIndividualCustomerResponse);
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllIndividualCustomersResponse>> getAll(){
		return this.individualCustomerService.getAll();
	}
}
