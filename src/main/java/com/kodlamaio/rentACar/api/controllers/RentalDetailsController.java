package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.RentalDetailsService;
import com.kodlamaio.rentACar.business.request.rentalDetails.CreateRentalDetailsRequest;
import com.kodlamaio.rentACar.business.request.rentalDetails.DeleteRentalDetailsRequest;
import com.kodlamaio.rentACar.business.request.rentalDetails.UpdateRentalDetailsRequest;
import com.kodlamaio.rentACar.business.response.rentalDetails.GetAllRentalDetailsResponse;
import com.kodlamaio.rentACar.business.response.rentalDetails.GetRentalDetailsResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.entities.concretes.RentalDetails;

@RestController
@RequestMapping("/api/rentaldetails")
public class RentalDetailsController {

	@Autowired
	private RentalDetailsService rentalDetailsService;

	@PostMapping("/add")
	public Result add(@RequestBody CreateRentalDetailsRequest createRentalRequest) {
		var result = rentalDetailsService.add(createRentalRequest);
		if (result.isSuccess()) {
			return new SuccessResult(result.getMessage());
		} else {
			return new ErrorResult(result.getMessage());
		}
	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateRentalDetailsRequest updateRentalRequest) {
		rentalDetailsService.update(updateRentalRequest);
		return new SuccessResult();
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteRentalDetailsRequest deleteRentalRequest) {
		rentalDetailsService.delete(deleteRentalRequest);
		return new SuccessResult();
	}

	@GetMapping("/getbyid")
	public DataResult<RentalDetails> getById(GetRentalDetailsResponse getRentalResponse) {

		return this.rentalDetailsService.getById(getRentalResponse);
	}

	@GetMapping("/getall")
	public DataResult<List<GetAllRentalDetailsResponse>> getall() {
		return this.rentalDetailsService.getAll();

	}
}
