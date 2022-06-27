package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdressService;
import com.kodlamaio.rentACar.business.request.adresses.CreateAdressRequest;
import com.kodlamaio.rentACar.business.request.adresses.CreateContactAdressRequest;
import com.kodlamaio.rentACar.business.request.adresses.CreateInvoiceAdressRequest;
import com.kodlamaio.rentACar.business.request.adresses.DeleteAdressRequest;
import com.kodlamaio.rentACar.business.request.adresses.UpdateAdressRequest;
import com.kodlamaio.rentACar.business.response.adresses.GetAdressResponse;
import com.kodlamaio.rentACar.business.response.adresses.GetAllAdressesResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdressRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CustomerRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.UserRepository;
import com.kodlamaio.rentACar.entities.concretes.Adress;
import com.kodlamaio.rentACar.entities.concretes.Customer;
import com.kodlamaio.rentACar.entities.concretes.User;

@Service
public class AdressManager implements AdressService{
	
	private AdressRepository addressRepository;
	private ModelMapperService modelMapperService;
	private UserRepository userRepository;
	private CustomerRepository customerRepository;
	
	@Autowired
	public AdressManager(AdressRepository addressRepository, ModelMapperService modelMapperService,
			UserRepository userRepository, CustomerRepository customerRepository) {
		super();
		this.addressRepository = addressRepository;
		this.modelMapperService = modelMapperService;
		this.userRepository = userRepository;
		this.customerRepository = customerRepository;
	}

	


	@Override
	public Result add(CreateAdressRequest createAdressRequest) {
		Adress address = this.modelMapperService.forRequest().map(createAdressRequest, Adress.class);
		this.addressRepository.save(address);
		return new SuccessResult("ADDRESS_ADDED");
	}
	
	@Override
	public Result addInvoiceAdress(CreateInvoiceAdressRequest createInvoiceAdressRequest) {
		Adress address = addressRepository.findById(createInvoiceAdressRequest.getCustomerId());
		address.setInvoiceAddress(createInvoiceAdressRequest.getInvoiceAddress());
		this.addressRepository.save(address);
		return new SuccessResult("INVOICE_ADDRESS_ADDED");
	}


	@Override
	public Result addContactAdress(CreateContactAdressRequest createContactAdressRequest) {
		Adress address = addressRepository.findById(createContactAdressRequest.getCustomerId());
		address.setInvoiceAddress(createContactAdressRequest.getContactAddress());
		this.addressRepository.save(address);
		return new SuccessResult("CONTACT_ADDRESS_ADDED");
	}

	@Override
	public Result update(UpdateAdressRequest updateAdressRequest) {
		Adress addressToUpdate = this.modelMapperService.forRequest().map(updateAdressRequest, Adress.class);
		this.addressRepository.save(addressToUpdate);
		return new SuccessResult("ADDRESS_UPDATED");
	}

	@Override
	public Result delete(DeleteAdressRequest deleteAdressRequest) {
		this.addressRepository.deleteById(deleteAdressRequest.getId());
		return new SuccessResult("ADDRESS_DELETED");
	}

	@Override
	public DataResult<List<GetAllAdressesResponse>> getAll() {

		List<Adress> addresses = this.addressRepository.findAll();

		List<GetAllAdressesResponse> response = addresses.stream()
				.map(address -> this.modelMapperService.forResponse().map(address, GetAllAdressesResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllAdressesResponse>>(response,"LISTING_ADDRESSES_SUCCESS");
	}

	@Override
	public DataResult<GetAdressResponse> getById(GetAdressResponse getAdressResponse) {
		Adress adress = this.addressRepository.findById(getAdressResponse.getId());
		GetAdressResponse response = this.modelMapperService.forResponse().map(adress, GetAdressResponse.class);
		return new SuccessDataResult<GetAdressResponse>(response,"LISTING_ADDRESSES_SUCCESS");

	}








}
