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
import com.kodlamaio.rentACar.business.response.rentalDetails.GetRentalDetailResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.OrderedAdditionalItemRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalDetailRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.OrderedAdditionalItem;
import com.kodlamaio.rentACar.entities.concretes.Rental;
import com.kodlamaio.rentACar.entities.concretes.RentalDetail;

@Service
public class RentalDetailManager implements RentalDetailsService{
	
	
	private RentalDetailRepository rentalDetailRepository;
	private RentalRepository rentalRepository;
	private OrderedAdditionalItemRepository orderedAdditionalItemRepository;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public RentalDetailManager(RentalDetailRepository rentalDetailRepository, RentalRepository rentalRepository,
			OrderedAdditionalItemRepository orderedAdditionalItemRepository, ModelMapperService modelMapperService) {
		super();
		this.rentalDetailRepository = rentalDetailRepository;
		this.rentalRepository = rentalRepository;
		this.orderedAdditionalItemRepository = orderedAdditionalItemRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateRentalDetailsRequest createRentalDetailRequest) {
		RentalDetail rentalDetail = this.modelMapperService.forRequest().map(createRentalDetailRequest,
				RentalDetail.class);

		this.rentalDetailRepository.save(rentalDetail);

		return new SuccessResult("RENTAL_DETAIL_ADDED");
	}

	@Override
	public Result update(UpdateRentalDetailsRequest updateRentalDetailRequest) {
		RentalDetail updateToRentalDetail = this.modelMapperService.forRequest().map(updateRentalDetailRequest,
				RentalDetail.class);

		this.rentalDetailRepository.save(updateToRentalDetail);

		return new SuccessResult("RENTAL_DETAIL_UPDATED");
	}

	@Override
	public Result delete(DeleteRentalDetailsRequest deleteRentalDetailsRequest) {
		RentalDetail rentalDetail = modelMapperService.forRequest().map(deleteRentalDetailsRequest, RentalDetail.class);
		rentalDetailRepository.delete(rentalDetail);
		return new SuccessResult("RENTAL_DETAIL_DELETED");
	}

	@Override
	public DataResult<RentalDetail> getById(GetRentalDetailResponse getRentalDetailResponse) {
		RentalDetail rentalDetail = this.modelMapperService.forResponse().map(getRentalDetailResponse, RentalDetail.class);
		rentalDetail = rentalDetailRepository.findById(getRentalDetailResponse.getId());
		return new SuccessDataResult<RentalDetail>(rentalDetail,"RENTAL_DETAIL_LISTED");
	}

	@Override
	public DataResult<List<GetAllRentalDetailsResponse>> getAll() {
		List<RentalDetail> rentalDetail = this.rentalDetailRepository.findAll();
		List<GetAllRentalDetailsResponse> response = rentalDetail.stream()
				.map(item -> this.modelMapperService.forResponse().map(item, GetAllRentalDetailsResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllRentalDetailsResponse>>(response,"ALL_RENTAL_DETAILS_LISTED");
	}

}
