package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import org.springframework.context.annotation.Lazy;

import com.kodlamaio.rentACar.business.request.rental.CreateRentalRequest;
import com.kodlamaio.rentACar.business.request.rental.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.request.rental.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.response.rentals.GetAllRentalsResponse;
import com.kodlamaio.rentACar.business.response.rentals.GetRentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Rental;


public interface RentalService {
	Result add(CreateRentalRequest createRentalRequest);

	Result update(UpdateRentalRequest updateRentalRequest);

	Result delete(DeleteRentalRequest deleteRentalRequest);

	DataResult<GetRentalResponse> getById(GetRentalResponse getRentalResponse);

	DataResult<List<GetAllRentalsResponse>> getAll();
	//DataResult<List<RentalDetails>> callRentalDetails();

}
