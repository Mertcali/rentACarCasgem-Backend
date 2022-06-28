package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.request.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.forCorporateCustomers.CreateRentalForCorporateRequest;
import com.kodlamaio.rentACar.business.request.rentals.forIndividualCustomers.CreateRentalForIndividualRequest;
import com.kodlamaio.rentACar.business.response.rentals.GetAllRentalsResponse;
import com.kodlamaio.rentACar.business.response.rentals.GetRentalResponse;
import com.kodlamaio.rentACar.business.response.rentals.forCorporateCustomers.GetAllRentalsForCorporateCustomersResponse;
import com.kodlamaio.rentACar.business.response.rentals.forCorporateCustomers.GetRentalForCorporateCustomerResponse;
import com.kodlamaio.rentACar.business.response.rentals.forIndividualCustomers.GetAllRentalsForIndividualCustomers;
import com.kodlamaio.rentACar.business.response.rentals.forIndividualCustomers.GetRentalForIndividualCustomer;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
	@Autowired
	RentalService rentalService;

	@PostMapping("/add")
	public Result add(@RequestBody CreateRentalRequest createRentalRequest) {

		return this.rentalService.add(createRentalRequest);

	}
	
	@PostMapping("/add/forindividual")
	public Result addRentalForIndividual(@RequestBody CreateRentalForIndividualRequest createRentalForIndividualRequest)
	{
		return this.rentalService.addRentalForIndividual(createRentalForIndividualRequest);
	}
	
	@PostMapping("/add/forcorporate")
	public Result addRentalForCorporate(@RequestBody CreateRentalForCorporateRequest createRentalForCorporateRequest)
	{
		return this.rentalService.addRentalForCorporate(createRentalForCorporateRequest);
	}
	

	@PostMapping("/update")
	public Result update(@RequestBody UpdateRentalRequest updateRentalRequest) {
		return this.rentalService.update(updateRentalRequest);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteRentalRequest deleteRentalRequest) {
		return this.rentalService.delete(deleteRentalRequest);

	}

	@GetMapping("/getbyid")
	public DataResult<GetRentalResponse> getById(@RequestBody GetRentalResponse getRentalResponse) {
		return this.rentalService.getById(getRentalResponse);
	}
	
	@GetMapping("/getby/corporatecustomerid")
	public DataResult<GetRentalForCorporateCustomerResponse> 
	getByCorporateCustomerId(@RequestBody GetRentalForCorporateCustomerResponse getRentalForCorporateCustomerResponse){
		return this.rentalService.getByCorporateCustomerId(getRentalForCorporateCustomerResponse);
	}
	
	@GetMapping("/getby/individualcustomerid")
	public DataResult<GetRentalForIndividualCustomer> getByIndividualCustomerId(@RequestBody GetRentalForIndividualCustomer getRentalForIndividualCustomer){
		return this.rentalService.getByIndividualCustomerId(getRentalForIndividualCustomer);
	}

	@GetMapping("/getall")
	public DataResult<List<GetAllRentalsResponse>> getAll() {
		return this.rentalService.getAll();
	}
	
	@GetMapping("/getall/forcorporatecustomers")
	public DataResult<List<GetAllRentalsForCorporateCustomersResponse>> getAllRentalsForCorporateCustomers(){
		return this.rentalService.getAllRentalsForCorporateCustomers();
		}
	
	@GetMapping("/getall/forindividualcustomers")
	public DataResult<List<GetAllRentalsForIndividualCustomers>> getAllRentalsForIndividualCustomers(){
		return this.rentalService.getAllRentalsForIndividualCustomers();
		}
	
	/*@GetMapping("getRentalDetails")
	public DataResult<List<RentalDetails>> getByRentalDetails(){
		return rentalService.callRentalDetails();
	}*/
}
