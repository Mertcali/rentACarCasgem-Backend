package com.kodlamaio.rentACar.core.utilities.adapters.abstracts;

import java.rmi.RemoteException;

import com.kodlamaio.rentACar.business.request.users.CreateUserRequest;

public interface UserCheckService {

	boolean checkUser(CreateUserRequest createUserRequest) throws NumberFormatException, RemoteException;
}
