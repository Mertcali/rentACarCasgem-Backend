package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CorporateCustomerService;
import com.kodlamaio.rentACar.business.request.corporateCustomers.CreateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.request.corporateCustomers.DeleteCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.request.corporateCustomers.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.response.corporateCustomers.GetAllCorporateCustomersResponse;
import com.kodlamaio.rentACar.business.response.corporateCustomers.GetCorporateCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CorporateCustomerRepository;
import com.kodlamaio.rentACar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

	private ModelMapperService modelMapperService;
	private CorporateCustomerRepository corporateCustomerRepository;
	
	@Autowired
	public CorporateCustomerManager(ModelMapperService modelMapperService,
			CorporateCustomerRepository corporateCustomerRepository) {
		super();
		this.modelMapperService = modelMapperService;
		this.corporateCustomerRepository = corporateCustomerRepository;
	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest()
				.map(createCorporateCustomerRequest, CorporateCustomer.class);
		corporateCustomerRepository.save(corporateCustomer);
		return new SuccessResult("CORPORATE_CUSTOMER_ADDED");
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest()
				.map(updateCorporateCustomerRequest, CorporateCustomer.class);
		corporateCustomerRepository.save(corporateCustomer);
		return new SuccessResult("CORPORATE_CUSTOMER_UPDATED");
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		corporateCustomerRepository.deleteById(deleteCorporateCustomerRequest.getId());
		return new SuccessResult("CORPORATE_CUSTOMER_DELETED");
	}

	@Override
	public DataResult<GetCorporateCustomerResponse> getById(GetCorporateCustomerResponse getCorporateCustomerResponse) {
		CorporateCustomer corporateCustomer = corporateCustomerRepository.findById(getCorporateCustomerResponse.getId());
		GetCorporateCustomerResponse response = this.modelMapperService.forResponse().map(corporateCustomer, GetCorporateCustomerResponse.class);
		return new SuccessDataResult<GetCorporateCustomerResponse>(response,"CORPORATE_CUSTOMER_LISTED");
	}

	@Override
	public DataResult<List<GetAllCorporateCustomersResponse>> getAll() {
		List<CorporateCustomer> corporateCustomers = corporateCustomerRepository.findAll();
		List<GetAllCorporateCustomersResponse> response= corporateCustomers.stream().map(corporateCustomer -> this.modelMapperService.forResponse()
				.map(corporateCustomer, GetAllCorporateCustomersResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCorporateCustomersResponse>>(response,"ALL_CORPORATE_CUSTOMERS_LISTED");
		
	}

}
