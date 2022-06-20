package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.request.rental.CreateRentalRequest;
import com.kodlamaio.rentACar.business.request.rental.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.request.rental.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.response.rentals.ReadRentalResponse;
import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.FindexService;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CityRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalDetailsRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.UserRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.City;
import com.kodlamaio.rentACar.entities.concretes.Rental;
import com.kodlamaio.rentACar.entities.concretes.User;
import com.kodlamaio.rentACar.entities.dtos.RentalDetails;

@Service
public class RentalManager implements RentalService {

	@Autowired
	RentalRepository rentalRepository;
	@Autowired
	CarRepository carRepository;
	@Autowired
	CityRepository cityRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ModelMapperService modelMapperService;
	@Autowired
	RentalDetailsRepository rentalDetailsRepository;
	@Autowired
	FindexService findexService;

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		Car car = this.carRepository.getById(createRentalRequest.getCarId());
		User user = userRepository.getById(createRentalRequest.getUserId());
		City pickUpCity= this.cityRepository.getById(createRentalRequest.getPickUpCityId());
		City returnCity= this.cityRepository.getById(createRentalRequest.getReturnCityId());
		car.setState(3);
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest,Rental.class);
		
		LocalDate date = createRentalRequest.getPickupDate();
		rental.setPickupDate(date);
		LocalDate returnvalue= date.plusDays(createRentalRequest.getTotalDays());
		rental.setReturnedDate(returnvalue);
		
		rental.setTotalPrice(createRentalRequest.getTotalDays() * car.getDailyPrice());// hesaplattırılacak
		
		rental.setCar(car);
		rental.setPickUpCity(pickUpCity);
		rental.setReturnCity(returnCity);
		
		if(rental.getPickUpCity().equals(rental.getReturnCity())) {
			
			rental.setTotalPrice(rental.getTotalPrice()+750);
		}
		
		if(checkFindexScore(car.getMinFindexScore(), user.getNationalIdentity())) {
			this.rentalRepository.save(rental);
			return new SuccessResult("ADDED.RENTAL");
		}else {
			throw new BusinessException("Findex_score_yetersiz");
		}

		
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Rental rental = new Rental();
		Car car = this.carRepository.getById(updateRentalRequest.getCarId());
		rental.setId(updateRentalRequest.getId());
		rental.setPickupDate(updateRentalRequest.getPickupDate());
		rental.setReturnedDate(updateRentalRequest.getReturnedDate());
		rental.setTotalDays(updateRentalRequest.getTotalDays());
		rental.setTotalPrice(updateRentalRequest.getTotalDays() * car.getDailyPrice());
		rental.setCar(car);

		this.rentalRepository.save(rental);

		return new SuccessResult("UPDATED.RENTAL");
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		Rental rental = new Rental();
		rental.setId(deleteRentalRequest.getId());
		this.rentalRepository.delete(rental);
		return new SuccessResult("DELETED.RENTAL");
	}

	@Override
	public DataResult<Rental> getById(ReadRentalResponse readRentalResponse) {
		return new SuccessDataResult<Rental>(this.rentalRepository.getById(readRentalResponse.getId()));
	}

	@Override
	public DataResult<List<Rental>> getAll() {
		return new SuccessDataResult<List<Rental>>(this.rentalRepository.findAll());
	}

	
	private boolean checkFindexScore(Integer findexScore, String nationalIdentity) {
		boolean state = false;
		if(findexService.findexScore(nationalIdentity) > findexScore) {
			state = true;
		}
		return state;
	}

	@Override
	public DataResult<List<RentalDetails>> callRentalDetails() {
		return new SuccessDataResult<List<RentalDetails>>(rentalDetailsRepository.callRentalDetails(),"Data Listelendi");
	}
	
}
