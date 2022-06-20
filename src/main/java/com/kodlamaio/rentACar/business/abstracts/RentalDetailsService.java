package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.rentalDetails.CreateRentalDetailsRequest;
import com.kodlamaio.rentACar.business.request.rentalDetails.DeleteRentalDetailsRequest;
import com.kodlamaio.rentACar.business.request.rentalDetails.UpdateRentalDetailsRequest;
import com.kodlamaio.rentACar.business.response.rentalDetails.GetAllRentalDetailsResponse;
import com.kodlamaio.rentACar.business.response.rentalDetails.GetRentalDetailsResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.RentalDetails;

public interface RentalDetailsService {

	Result add(CreateRentalDetailsRequest createRentalDetailsRequest);

	Result update(UpdateRentalDetailsRequest updateRentalDetailsRequest);

	Result delete(DeleteRentalDetailsRequest deleteRentalDetailsRequest);

	DataResult<RentalDetails> getById(GetRentalDetailsResponse getRentalDetailsResponse);

	DataResult<List<GetAllRentalDetailsResponse>> getAll();
}
