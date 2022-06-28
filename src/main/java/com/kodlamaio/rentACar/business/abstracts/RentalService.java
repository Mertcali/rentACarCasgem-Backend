package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import org.springframework.context.annotation.Lazy;

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


public interface RentalService {
	Result add(CreateRentalRequest createRentalRequest);
	Result addRentalForIndividual(CreateRentalForIndividualRequest createRentalForIndividualRequest);
	Result addRentalForCorporate(CreateRentalForCorporateRequest createRentalForCorporateRequest);

	Result update(UpdateRentalRequest updateRentalRequest);

	Result delete(DeleteRentalRequest deleteRentalRequest);

	DataResult<GetRentalResponse> getById(GetRentalResponse getRentalResponse);
	DataResult<GetRentalForCorporateCustomerResponse> getByCorporateCustomerId(GetRentalForCorporateCustomerResponse getRentalForCorporateCustomerResponse);
	DataResult<GetRentalForIndividualCustomer> getByIndividualCustomerId(GetRentalForIndividualCustomer getRentalForIndividualCustomer);
	
	DataResult<List<GetAllRentalsForCorporateCustomersResponse>> getAllRentalsForCorporateCustomers();
	DataResult<List<GetAllRentalsForIndividualCustomers>> getAllRentalsForIndividualCustomers();
	DataResult<List<GetAllRentalsResponse>> getAll();
	//DataResult<List<RentalDetails>> callRentalDetails();

}
