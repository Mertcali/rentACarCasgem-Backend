package com.kodlamaio.rentACar.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.request.invoices.DeleteInvoiceRequest;
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
import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.FindexService;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CityRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CorporateCustomerRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CustomerRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.IndividualCustomerRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.InvoiceRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.OrderedAdditionalItemRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.UserRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.CorporateCustomer;
import com.kodlamaio.rentACar.entities.concretes.Customer;
import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;
import com.kodlamaio.rentACar.entities.concretes.Invoice;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {

	
	private RentalRepository rentalRepository;
	private CarRepository carRepository;
	private CityRepository cityRepository;
	private UserRepository userRepository;
	private ModelMapperService modelMapperService;
	private FindexService findexService;
	private IndividualCustomerRepository individualCustomerRepository;
	private CustomerRepository customerRepository;
	private InvoiceRepository invoiceRepository;
	private InvoiceService invoiceService;
	private OrderedAdditionalItemRepository orderedAdditionalItemRepository;
	private CorporateCustomerRepository corporateCustomerRepository;
	
	@Autowired
	public RentalManager(RentalRepository rentalRepository, CarRepository carRepository, CityRepository cityRepository,
			UserRepository userRepository, ModelMapperService modelMapperService,
		    FindexService findexService,
			IndividualCustomerRepository individualCustomerRepository, CustomerRepository customerRepository,
			InvoiceRepository invoiceRepository, InvoiceService invoiceService, 
			OrderedAdditionalItemRepository orderedAdditionalItemRepository,
			CorporateCustomerRepository corporateCustomerRepository) {
		super();
		this.rentalRepository = rentalRepository;
		this.carRepository = carRepository;
		this.cityRepository = cityRepository;
		this.userRepository = userRepository;
		this.modelMapperService = modelMapperService;
		this.findexService = findexService;
		this.individualCustomerRepository = individualCustomerRepository;
		this.customerRepository = customerRepository;
		this.invoiceRepository = invoiceRepository;
		this.invoiceService = invoiceService;
		this.orderedAdditionalItemRepository = orderedAdditionalItemRepository;
		this.corporateCustomerRepository=corporateCustomerRepository;	
		}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {

		checkIfCarExistsById(createRentalRequest.getCarId());
		checkIfCustomersExistsById(createRentalRequest.getCustomerId()); 
		checkCarState(createRentalRequest.getCarId());
		
		Rental rentalToAdd = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		//OrderedAdditionalItem orderedAdditionalItem = orderedAdditionalItemRepository.findById(createRentalRequest.getOrderedAdditionalItemId());
		Integer orderedAdditionalItemId = createRentalRequest.getOrderedAdditionalItemId();
		Customer customer = this.customerRepository.findById(createRentalRequest.getCustomerId());
		Car carToRent = carRepository.findById(rentalToAdd.getCar().getId());
		int orderedAdditionalItemIdInt = orderedAdditionalItemId;
		rentalToAdd.setOrderedAdditionalItem(orderedAdditionalItemRepository.findById(orderedAdditionalItemIdInt));
		rentalToAdd.setTotalDaysRental(calculateTotalDay(createRentalRequest.getPickupDate(), createRentalRequest.getReturnDate()));
		checkFindexScore(carToRent.getMinFindexScore(), customer.getFindexScore());	
			
		if(orderedAdditionalItemId!=null) {		
			checkCityStateAndAdditionalItemAndCalculatePrice(rentalToAdd,carToRent);		
		}else {
			checkCityStateAndCalculatePrice(rentalToAdd, carToRent);			
		}
		
		//cleanCode'a karşı bir integer yapısı ve kontrol şekli kullandım.Bunlar düzenlenmeli.
		//IND VE CORP AYRILMALI
		
		carToRent.setState(1);
		//STATE-0 Müsait
		//STATE-1 Araç kiralanmış
		//STATE-2 Araç bakımda

		rentalRepository.save(rentalToAdd);
		
		//addInvoice(rentalToAdd);
		
		return new SuccessResult("RENTAL_ADDED_SUCCESSFULLY");

	}
	
	@Override
	public Result addRentalForIndividual(CreateRentalForIndividualRequest createRentalForIndividualRequest) {
		checkIfCarExistsById(createRentalForIndividualRequest.getCarId());
		checkIfCustomersExistsById(createRentalForIndividualRequest.getIndividualCustomerId()); // individual için düzenlenecek
		checkCarState(createRentalForIndividualRequest.getCarId());
		
		Rental rentalToAdd = this.modelMapperService.forRequest().map(createRentalForIndividualRequest, Rental.class);
		Integer orderedAdditionalItemId = createRentalForIndividualRequest.getOrderedAdditionalItemId();
		IndividualCustomer individualCustomer = this.individualCustomerRepository.findById(createRentalForIndividualRequest.getIndividualCustomerId());
		Car carToRent = carRepository.findById(rentalToAdd.getCar().getId());
		int orderedAdditionalItemIdInt = orderedAdditionalItemId;
		rentalToAdd.setOrderedAdditionalItem(orderedAdditionalItemRepository.findById(orderedAdditionalItemIdInt));
		rentalToAdd.setTotalDaysRental(calculateTotalDay(createRentalForIndividualRequest.getPickupDate(), createRentalForIndividualRequest.getReturnDate()));
		checkFindexScore(carToRent.getMinFindexScore(), individualCustomer.getFindexScore());	
		
		if(orderedAdditionalItemId!=null) {		
			checkCityStateAndAdditionalItemAndCalculatePrice(rentalToAdd,carToRent);		
		}else {
			checkCityStateAndCalculatePrice(rentalToAdd, carToRent);			
		}
		carToRent.setState(1);
		rentalRepository.save(rentalToAdd);
		return new SuccessResult("RENTAL_ADDED_SUCCESSFULLY");
		
	}

	@Override
	public Result addRentalForCorporate(CreateRentalForCorporateRequest createRentalForCorporateRequest) {
		checkIfCarExistsById(createRentalForCorporateRequest.getCarId());
		checkIfCustomersExistsById(createRentalForCorporateRequest.getCorporateCustomerId());
		checkCarState(createRentalForCorporateRequest.getCarId());
		
		Rental rentalToAdd = this.modelMapperService.forRequest().map(createRentalForCorporateRequest, Rental.class);
		Integer orderedAdditionalItemId = createRentalForCorporateRequest.getOrderedAdditionalItemId();
		CorporateCustomer corporateCustomer = this.corporateCustomerRepository.findById(createRentalForCorporateRequest.getCorporateCustomerId());
		Car carToRent = carRepository.findById(rentalToAdd.getCar().getId());
		int orderedAdditionalItemIdInt = orderedAdditionalItemId;
		rentalToAdd.setOrderedAdditionalItem(orderedAdditionalItemRepository.findById(orderedAdditionalItemIdInt));
		rentalToAdd.setTotalDaysRental(calculateTotalDay(createRentalForCorporateRequest.getPickupDate(), createRentalForCorporateRequest.getReturnDate()));
		checkFindexScore(carToRent.getMinFindexScore(), corporateCustomer.getFindexScore());	
		
		if(orderedAdditionalItemId!=null) {		
			checkCityStateAndAdditionalItemAndCalculatePrice(rentalToAdd,carToRent);		
		}else {
			checkCityStateAndCalculatePrice(rentalToAdd, carToRent);			
		}
		carToRent.setState(1);
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
		Rental rental2 = rentalRepository.findById(rental.getId());
		System.out.println(rental2.getTotalDaysRental());
		Car car = rental2.getCar();
		deleteInvoiceForDeletingRental(rental2);
		car.setState(0);
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
	public DataResult<GetRentalForCorporateCustomerResponse> getByCorporateCustomerId(
			GetRentalForCorporateCustomerResponse getRentalForCorporateCustomerResponse) {
		Rental rental = this.rentalRepository.findById(getRentalForCorporateCustomerResponse.getId());
		GetRentalForCorporateCustomerResponse response = this.modelMapperService.forResponse().map(rental, GetRentalForCorporateCustomerResponse.class);
		return new SuccessDataResult<GetRentalForCorporateCustomerResponse>(response,"RENTAL_FOR_CORPORATE_LISTED");
	}

	@Override
	public DataResult<GetRentalForIndividualCustomer> getByIndividualCustomerId(
			GetRentalForIndividualCustomer getRentalForIndividualCustomer) {
		Rental rental = this.rentalRepository.findById(getRentalForIndividualCustomer.getId());
		GetRentalForIndividualCustomer response = this.modelMapperService.forResponse().map(rental, GetRentalForIndividualCustomer.class);
		return new SuccessDataResult<GetRentalForIndividualCustomer>(response,"RENTAL_FOR_CORPORATE_LISTED");
	}

	@Override
	public DataResult<List<GetAllRentalsResponse>> getAll() {
		
		List<Rental> rentals = this.rentalRepository.findAll();
		List<GetAllRentalsResponse> response = rentals.stream()
				.map(rental -> this.modelMapperService.forResponse().map(rental, GetAllRentalsResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllRentalsResponse>>(response,"ALL_RENTALS_LISTED");
		
		//return new SuccessDataResult<List<Rental>>(this.rentalRepository.findAll(),"ALL_RENTALS_LISTED");
	}
	

	@Override
	public DataResult<List<GetAllRentalsForCorporateCustomersResponse>> getAllRentalsForCorporateCustomers() {
		List<Rental> rentals = this.rentalRepository.findAll();
		List<GetAllRentalsForCorporateCustomersResponse> response = rentals.stream()
				.map(rental -> this.modelMapperService.forResponse().map(rental, GetAllRentalsForCorporateCustomersResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllRentalsForCorporateCustomersResponse>>(response,"ALL_RENTALS_FOR_CORPORATE_LISTED");
	}

	@Override
	public DataResult<List<GetAllRentalsForIndividualCustomers>> getAllRentalsForIndividualCustomers() {
		List<Rental> rentals = this.rentalRepository.findAll();
		List<GetAllRentalsForIndividualCustomers> response = rentals.stream()
				.map(rental -> this.modelMapperService.forResponse().map(rental, GetAllRentalsForIndividualCustomers.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllRentalsForIndividualCustomers>>(response,"ALL_RENTALS_FOR_CORPORATE_LISTED");
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
	
	/*private void addInvoice(Rental addedRental) {
		Random random = new Random();
		int sayi = random.nextInt(999999)+1000000;
		String stringSayi = String.valueOf(sayi);
		CreateInvoiceRequest createInvoiceRequest = new CreateInvoiceRequest(stringSayi,addedRental.getId());
		invoiceService.add(createInvoiceRequest);
	}*/
	
	private void checkCityStateAndCalculatePrice(Rental rentalToAdd, Car carToRent) {
		if(rentalToAdd.getPickupCityId() != rentalToAdd.getReturnCityId()) {
			rentalToAdd.setTotalPrice((rentalToAdd.getTotalDaysRental() * carToRent.getDailyPrice())+750);
		}else {
			rentalToAdd.setTotalPrice((rentalToAdd.getTotalDaysRental() * carToRent.getDailyPrice()));
		}	
	}
	
	private void checkCityStateAndAdditionalItemAndCalculatePrice(Rental rentalToAdd, Car carToRent) {
		if(rentalToAdd.getPickupCityId() != rentalToAdd.getReturnCityId()) {
			rentalToAdd.setTotalPrice((rentalToAdd.getTotalDaysRental() * carToRent.getDailyPrice())+ rentalToAdd.getOrderedAdditionalItem().getTotalPrice()+750);
		}else {
			rentalToAdd.setTotalPrice((rentalToAdd.getTotalDaysRental() * carToRent.getDailyPrice())+ rentalToAdd.getOrderedAdditionalItem().getTotalPrice());
		}
	}
	
	private void deleteInvoiceForDeletingRental(Rental rental) {
		Invoice invoice = invoiceRepository.findByRentalId(rental.getId());	
		DeleteInvoiceRequest deleteInvoiceRequest = new DeleteInvoiceRequest(invoice.getId());	
		this.invoiceService.delete(deleteInvoiceRequest);
	}




	

}
	

