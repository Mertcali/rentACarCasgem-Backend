package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.request.cars.CreateCarRequest;
import com.kodlamaio.rentACar.business.request.cars.DeleteCarRequest;
import com.kodlamaio.rentACar.business.request.cars.UpdateCarRequest;
import com.kodlamaio.rentACar.business.response.cars.GetAllCarsResponse;
import com.kodlamaio.rentACar.business.response.cars.GetCarResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.entities.concretes.Brand;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Color;

@Service
public class CarManager implements CarService {

	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CarManager(CarRepository carRepository, ModelMapperService modelMapperService) {
		super();
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {

		//Color color = this.modelMapperService.forRequest().map(createCarRequest, Color.class);
		//Brand brand = this.modelMapperService.forRequest().map(createCarRequest, Brand.class);
		checkIfCarExistsByPlate(createCarRequest.getPlate());
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		
//		if (carRepository.getByBrandId(createCarRequest.getBrandId()).size() < 5) {
//			this.carRepository.save(car);
//			return new SuccessResult("CAR.ADDED");
//		} else {
//			return new ErrorResult("Not");
//		}

		if (maxBrand(createCarRequest.getBrandId())) {
			this.carRepository.save(car);
			return new SuccessResult("CAR_ADDED");
		} else {
			return new ErrorResult("NUMBER_OF_CARS_EXCEEDED");
		}

	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
//		Color color = new Color();
//		Brand brand = new Brand();
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
//		car.setDescription(updateCarRequest.getDescription());
//		car.setDailyPrice(updateCarRequest.getDailyPrice());
		
//		brand.setId(updateCarRequest.getBrandId());
//		color.setId(updateCarRequest.getBrandId());
//		car.setBrand(brand);
//		car.setColor(color);
//		car.setPlate(updateCarRequest.getPlate());
//		car.setKilometer(updateCarRequest.getKilometer());

		this.carRepository.save(car);
		return new SuccessResult("CAR_UPDATED");

	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		Car car = this.modelMapperService.forRequest().map(deleteCarRequest, Car.class);
		this.carRepository.delete(car);
		return new SuccessResult("CAR_DELETED");

	}


	@Override
	public DataResult<List<GetAllCarsResponse>> getAll() {
		List<Car> cars = this.carRepository.findAll();
		List<GetAllCarsResponse> response = 
				cars.stream().map(car -> this.modelMapperService.forResponse()
						.map(car, GetAllCarsResponse.class))
						.collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllCarsResponse>>(response,"ALL_CARS_LISTED");
	}

	@Override
	public DataResult<GetCarResponse> getById(GetCarResponse getCarResponse) {
		Car car = this.carRepository.findById(getCarResponse.getId());
		GetCarResponse response = this.modelMapperService.forResponse().map(car, GetCarResponse.class);
		return new SuccessDataResult<GetCarResponse>(response,"CAR_LISTED");
	}
	
	private void checkIfCarExistsByPlate(String plate) {
		Car currentCar = carRepository.findByPlate(plate);
		if(currentCar!=null) {
			throw new BusinessException("CAR_EXISTS");
		}
	}
	
	private boolean maxBrand(int brandId) {
		boolean exits = false;
		if (carRepository.findByBrandId(brandId).size() < 5) {
			exits = true;
		}
		return exits;
	}

}

