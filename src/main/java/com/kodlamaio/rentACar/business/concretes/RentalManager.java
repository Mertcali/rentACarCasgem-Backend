package com.kodlamaio.rentACar.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.request.rental.CreateRentalRequest;
import com.kodlamaio.rentACar.business.request.rental.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.request.rental.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.response.rentals.GetAllRentalsResponse;
import com.kodlamaio.rentACar.business.response.rentals.GetRentalResponse;
import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.FindexService;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CityRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CustomerRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.IndividualCustomerRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.InvoiceRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalDetailRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.UserRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Customer;
import com.kodlamaio.rentACar.entities.concretes.Invoice;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {

	
	private RentalRepository rentalRepository;
	private CarRepository carRepository;
	private CityRepository cityRepository;
	private UserRepository userRepository;
	private ModelMapperService modelMapperService;
	private RentalDetailRepository rentalDetailRepository;
	private FindexService findexService;
	private IndividualCustomerRepository individualCustomerRepository;
	private CustomerRepository customerRepository;
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	public RentalManager(RentalRepository rentalRepository, CarRepository carRepository, CityRepository cityRepository,
			UserRepository userRepository, ModelMapperService modelMapperService,
			RentalDetailRepository rentalDetailRepository, FindexService findexService,
			IndividualCustomerRepository individualCustomerRepository, CustomerRepository customerRepository,
			InvoiceRepository invoiceRepository) {
		super();
		this.rentalRepository = rentalRepository;
		this.carRepository = carRepository;
		this.cityRepository = cityRepository;
		this.userRepository = userRepository;
		this.modelMapperService = modelMapperService;
		this.rentalDetailRepository = rentalDetailRepository;
		this.findexService = findexService;
		this.individualCustomerRepository = individualCustomerRepository;
		this.customerRepository = customerRepository;
		this.invoiceRepository = invoiceRepository;
	}

	


	@Override
	public Result add(CreateRentalRequest createRentalRequest) {

		checkIfCarExistsById(createRentalRequest.getCarId());
		checkIfCustomersExistsById(createRentalRequest.getCustomerId());
		checkCarState(createRentalRequest.getCarId());
		
		Rental rentalToAdd = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		Customer customer = this.customerRepository.findById(createRentalRequest.getCustomerId());
		Car carToRent = carRepository.findById(rentalToAdd.getCar().getId());
		rentalToAdd.setTotalDays(calculateTotalDay(createRentalRequest.getPickupDate(), createRentalRequest.getReturnDate()));
		checkFindexScore(carToRent.getMinFindexScore(), customer.getFindexScore());	
		//BAŞKA ŞEHİRDE TESLİM EDİLECEKSE +750 TL EKLE METOTLAŞTIRILABİLİR
		//KİRALANAN TARİH ARALIĞI KİRALANABİLME İÇİN KONTROL EDİLMELİ, EKLENECEK
		//ARAÇ KİRALAMADAN SONRA FATURA OLUŞTURULMALI
		//ADDITIONAL EKLEMELERİ GELMELİ
		if(rentalToAdd.getPickupCityId() != rentalToAdd.getReturnCityId()) {
			rentalToAdd.setTotalPrice((rentalToAdd.getTotalDays() * carToRent.getDailyPrice())+750);
		}else {
			rentalToAdd.setTotalPrice((rentalToAdd.getTotalDays() * carToRent.getDailyPrice()));
		}	
		carToRent.setState(1);
		//STATE-0 Müsait
		//STATE-1 Araç kiralanmış
		//STATE-2 Araç bakımda
		
		rentalRepository.save(rentalToAdd);
		
		
		return new SuccessResult("RENTAL_ADDED_SUCCESSFULLY");

	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		this.rentalRepository.save(rental);

		return new SuccessResult("RENTAL_UPDATED");
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
		this.rentalRepository.delete(rental);
		return new SuccessResult("RENTAL_DELETED");
	}

	@Override
	public DataResult<GetRentalResponse> getById(GetRentalResponse getRentalResponse) {
		Rental rental = this.rentalRepository.findById(getRentalResponse.getId());
		GetRentalResponse response = this.modelMapperService.forResponse().map(rental, GetRentalResponse.class);
		return new SuccessDataResult<GetRentalResponse>(response,"RENTAL_LISTED");
	}

	@Override
	public DataResult<List<GetAllRentalsResponse>> getAll() {
		
		List<Rental> rentals = this.rentalRepository.findAll();
		List<GetAllRentalsResponse> response = rentals.stream()
				.map(rental -> this.modelMapperService.forResponse().map(rental, GetAllRentalsResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllRentalsResponse>>(response,"ALL_RENTALS_LISTED");
		
		//return new SuccessDataResult<List<Rental>>(this.rentalRepository.findAll(),"ALL_RENTALS_LISTED");
	}

	
	/*@Override
	public DataResult<List<RentalDetails>> callRentalDetails() {
		return new SuccessDataResult<List<RentalDetails>>(rentalDetailsRepository.callRentalDetails(),"Data Listelendi");
	}*/
	
	private boolean checkFindexScore(Integer findexScore, Integer customerFindex) {
		if(findexScore > customerFindex) {
			throw new BusinessException("FINDEX_SCORE_NOT_ENOUGH");
		}
		else {
			return true;
		}
}

	private int calculateTotalDay(Date pickUpDate, Date returnDate) {
		long dayDifference = (returnDate.getTime() - pickUpDate.getTime());

		if(dayDifference<0)
		{
			throw new BusinessException("WRONG_DATE");
		}else {
			long timeToInt = TimeUnit.DAYS.convert(dayDifference, TimeUnit.MILLISECONDS);
			int days = (int) timeToInt;
			return days;
		}
	
	}
	
	private void checkIfCarExistsById(int carId) {

		Car currentCar = carRepository.findById(carId);
		if(currentCar==null) {
			throw new BusinessException("CAR_DOES_NOT_EXIST");
		}
		
	}
	private void checkIfCustomersExistsById(int id) {
		Customer currentCustomer = customerRepository.findById(id); //existById kullanıp boolean döndürülebilirmiş.
		if (currentCustomer == null) {
			throw new BusinessException("CUSTOMER_DOES_NOT_EXIST");
		}
	}
	
	private void checkCarState(int carId) {
		Car carToCheck = carRepository.findById(carId);
		if (carToCheck.getState() == 1 || carToCheck.getState()==2) {
			throw new BusinessException("CHECK_CAR_STATE");
		} 
	}
	
	
}
