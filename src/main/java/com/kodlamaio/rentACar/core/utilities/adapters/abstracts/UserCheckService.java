package com.kodlamaio.rentACar.core.utilities.adapters.abstracts;

import java.rmi.RemoteException;

import com.kodlamaio.rentACar.business.request.individualCustomers.CreateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.request.users.CreateUserRequest;

public interface UserCheckService {

	boolean checkIndividualCustomer(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NumberFormatException, RemoteException;
}
