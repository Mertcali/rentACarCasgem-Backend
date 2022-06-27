package com.kodlamaio.rentACar.business.abstracts;

import java.rmi.RemoteException;
import java.util.List;

import com.kodlamaio.rentACar.business.request.customers.CreateCustomerRequest;
import com.kodlamaio.rentACar.business.request.customers.DeleteCustomerRequest;
import com.kodlamaio.rentACar.business.request.customers.UpdateCustomerRequest;
import com.kodlamaio.rentACar.business.request.individualCustomers.CreateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.request.individualCustomers.DeleteIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.request.individualCustomers.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.response.customers.GetAllCustomersResponse;
import com.kodlamaio.rentACar.business.response.customers.GetCustomerResponse;
import com.kodlamaio.rentACar.business.response.individualCustomers.GetAllIndividualCustomersResponse;
import com.kodlamaio.rentACar.business.response.individualCustomers.GetIndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface IndividualCustomerService {

	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NumberFormatException, RemoteException;

	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws NumberFormatException, RemoteException;

	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest);

	DataResult<GetIndividualCustomerResponse> getById(GetIndividualCustomerResponse getIndividualCustomerResponse);

	DataResult<List<GetAllIndividualCustomersResponse>> getAll();
}
