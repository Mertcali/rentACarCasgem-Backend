package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.RentalDetailsService;
import com.kodlamaio.rentACar.business.request.rentalDetails.CreateRentalDetailsRequest;
import com.kodlamaio.rentACar.business.request.rentalDetails.DeleteRentalDetailsRequest;
import com.kodlamaio.rentACar.business.request.rentalDetails.UpdateRentalDetailsRequest;
import com.kodlamaio.rentACar.business.response.rentalDetails.GetAllRentalDetailsResponse;
import com.kodlamaio.rentACar.business.response.rentalDetails.GetRentalDetailsResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalDetailsRepository;
import com.kodlamaio.rentACar.entities.concretes.RentalDetails;

@Service
public class RentalDetailsManager implements RentalDetailsService{
	
	@Autowired
	private RentalDetailsRepository rentalDetailsRepository;
	@Autowired
	private ModelMapperService modelMapperService;
	@Override
	public Result add(CreateRentalDetailsRequest createRentalDetailsRequest) {
		RentalDetails rentalDetails = modelMapperService.forRequest().map(createRentalDetailsRequest, RentalDetails.class);
		rentalDetailsRepository.save(rentalDetails);
		return new SuccessResult("Kira detay eklendi");
	}

	@Override
	public Result update(UpdateRentalDetailsRequest updateRentalDetailsRequest) {
		RentalDetails rentalDetailToUpdate = modelMapperService.forRequest().map(updateRentalDetailsRequest,
				RentalDetails.class);
		rentalDetailsRepository.save(rentalDetailToUpdate);
		return new SuccessResult("Kira detay güncellendi");
	}

	@Override
	public Result delete(DeleteRentalDetailsRequest deleteRentalDetailsRequest) {
		RentalDetails rentalDetails = modelMapperService.forRequest().map(deleteRentalDetailsRequest, RentalDetails.class);
		rentalDetailsRepository.delete(rentalDetails);
		return new SuccessResult("Kira detay silindi");
	}

	@Override
	public DataResult<RentalDetails> getById(GetRentalDetailsResponse getRentalDetailsResponse) {
		RentalDetails rentalDetails = this.modelMapperService.forResponse().map(getRentalDetailsResponse, RentalDetails.class);
		rentalDetails = rentalDetailsRepository.findById(getRentalDetailsResponse.getId()).get();
		return new SuccessDataResult<RentalDetails>(rentalDetails);
	}

	@Override
	public DataResult<List<GetAllRentalDetailsResponse>> getAll() {
		List<RentalDetails> rentalDetails = this.rentalDetailsRepository.findAll();
		List<GetAllRentalDetailsResponse> response = rentalDetails.stream()
				.map(item -> this.modelMapperService.forResponse().map(item, GetAllRentalDetailsResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllRentalDetailsResponse>>(response);
	}

}
