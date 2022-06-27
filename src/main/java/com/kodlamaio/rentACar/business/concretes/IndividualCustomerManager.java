package com.kodlamaio.rentACar.business.concretes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerService;
import com.kodlamaio.rentACar.business.request.individualCustomers.CreateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.request.individualCustomers.DeleteIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.request.individualCustomers.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.response.individualCustomers.GetAllIndividualCustomersResponse;
import com.kodlamaio.rentACar.business.response.individualCustomers.GetIndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.UserCheckService;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.IndividualCustomerRepository;
import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService{

	private ModelMapperService modelMapperService;
	private IndividualCustomerRepository individualCustomerRepository;
	private UserCheckService userCheckService;
	
	@Autowired

	public IndividualCustomerManager(ModelMapperService modelMapperService,
			IndividualCustomerRepository individualCustomerRepository, UserCheckService userCheckService) {
		super();
		this.modelMapperService = modelMapperService;
		this.individualCustomerRepository = individualCustomerRepository;
		this.userCheckService = userCheckService;
	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NumberFormatException, RemoteException {
		checkPerson(createIndividualCustomerRequest);
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerRepository.save(individualCustomer);
		return new SuccessResult("INDIVIDUAL_CUSTOMER_ADDED");
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws NumberFormatException, RemoteException {
		CreateIndividualCustomerRequest createIndividualCustomerRequest = this.modelMapperService.forRequest().map(updateIndividualCustomerRequest, CreateIndividualCustomerRequest.class);
		checkPerson(createIndividualCustomerRequest);
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerRepository.save(individualCustomer);
		return new SuccessResult("INDIVIDUAL_CUSTOMER_UPDATED");
	}

	@Override
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		this.individualCustomerRepository.deleteById(deleteIndividualCustomerRequest.getId());
		return new SuccessResult("INDIVIDUAL_CUSTOMER_DELETED");
	}

	@Override
	public DataResult<GetIndividualCustomerResponse> getById(GetIndividualCustomerResponse getIndividualCustomerResponse) 
	{
		IndividualCustomer individualCustomer = this.individualCustomerRepository
				.findById(getIndividualCustomerResponse.getIndividualCustomerId());
		GetIndividualCustomerResponse response = this.modelMapperService.forResponse().map(individualCustomer, GetIndividualCustomerResponse.class);
		return new SuccessDataResult<GetIndividualCustomerResponse>(response,"INDIVIDUAL_CUSTOMER_LISTED");
	}

	@Override
	public DataResult<List<GetAllIndividualCustomersResponse>> getAll() {
		List<IndividualCustomer> customers = this.individualCustomerRepository.findAll();
		List<GetAllIndividualCustomersResponse> response = customers.stream().map(customer -> this.modelMapperService.forResponse().map(customer, GetAllIndividualCustomersResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllIndividualCustomersResponse>>(response,"ALL_INDIVIDUAL_CUSTOMERS_LISTED");
	}
	
	public void checkPerson(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NumberFormatException, RemoteException {
		
		if(!userCheckService.checkIndividualCustomer(createIndividualCustomerRequest)) {
			throw new BusinessException("TC_VATANDASI_DEGIL");
		}
			
	}

}
