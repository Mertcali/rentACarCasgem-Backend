package com.kodlamaio.rentACar.business.abstracts;

import java.rmi.RemoteException;
import java.util.List;

import com.kodlamaio.rentACar.business.request.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.request.users.DeleteUserRequest;
import com.kodlamaio.rentACar.business.request.users.UpdateUserRequest;
import com.kodlamaio.rentACar.business.response.users.GetAllUsersResponse;
import com.kodlamaio.rentACar.business.response.users.GetUserResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.User;

public interface UserService {
	
	Result add(CreateUserRequest createUserRequest) throws NumberFormatException, RemoteException;
	Result delete(DeleteUserRequest deleteUserRequest);
	Result update(UpdateUserRequest updateUserRequest);
	
	DataResult<List<User>> getAll(int pageNo, int pageSize);
	DataResult<GetUserResponse> getByNationaIdentity(GetUserResponse getUserResponse);
	DataResult<List<GetAllUsersResponse>> getAll();
	

}
