package com.kodlamaio.rentACar.business.concretes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.UserService;
import com.kodlamaio.rentACar.business.request.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.request.users.DeleteUserRequest;
import com.kodlamaio.rentACar.business.request.users.UpdateUserRequest;
import com.kodlamaio.rentACar.business.response.users.GetAllUsersResponse;
import com.kodlamaio.rentACar.business.response.users.ReadUserResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.UserRepository;
import com.kodlamaio.rentACar.entities.concretes.User;

@Service
public class UserManager implements UserService{

	@Autowired
	private ModelMapperService modelMapperService;
	
	@Autowired private UserRepository userRepository;
	
	@Autowired private com.kodlamaio.rentACar.core.utilities.adapters.abstracts.UserCheckService userCheckService;
	
	
	@Override
	public Result add(CreateUserRequest createUserRequest) throws NumberFormatException, RemoteException {
		checkPerson(createUserRequest);
		checkIfUserExists(createUserRequest.getUserNationalIdentity());
		User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
		this.userRepository.save(user);
		return new SuccessResult("ADDED_USER");
	}

	@Override
	public Result delete(DeleteUserRequest deleteUserRequest) {
		User user = this.modelMapperService.forRequest().map(deleteUserRequest, User.class);
		this.userRepository.delete(user);
		return new SuccessResult("DELETED_USER");
	}

	@Override
	public Result update(UpdateUserRequest updateUserRequest) {
		User user = this.modelMapperService.forRequest().map(updateUserRequest, User.class);
		this.userRepository.save(user);
		return new SuccessResult("UPDATED_USER");
	}


	@Override
	public DataResult<List<GetAllUsersResponse>> getAll() {
		List<User> users = this.userRepository.findAll();
		List<GetAllUsersResponse> response = 
				users.stream().map(user -> this.modelMapperService.forResponse()
						.map(user, GetAllUsersResponse.class))
						.collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllUsersResponse>>(response);
	}
	
	private void checkIfUserExists(String identity) {
		User currentUser = userRepository.getByNationalIdentity(identity);
		if(currentUser!=null) {
			throw new BusinessException("USER_EXISTS");
		}
	}

	@Override
	public DataResult<User> getByNationaIdentity(ReadUserResponse readUserResponse) {
		return new SuccessDataResult<User>(this.userRepository.getByNationalIdentity(readUserResponse.getNationalIdentity()));
	}

	@Override
	public DataResult<List<User>> getAll(int pageNo, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		
		return new SuccessDataResult<List<User>>(userRepository.findAll(pageable).getContent());
	}

	
	public void checkPerson(CreateUserRequest createUserRequest) throws NumberFormatException, RemoteException {
		
		if(!userCheckService.checkUser(createUserRequest)) {
			throw new BusinessException("TC_VATANDASI_DEGIL");
		}
			
	}

}
