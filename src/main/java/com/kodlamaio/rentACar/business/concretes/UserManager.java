package com.kodlamaio.rentACar.business.concretes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.UserService;
import com.kodlamaio.rentACar.business.request.individualCustomers.CreateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.request.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.request.users.DeleteUserRequest;
import com.kodlamaio.rentACar.business.request.users.UpdateUserRequest;
import com.kodlamaio.rentACar.business.response.users.GetAllUsersResponse;
import com.kodlamaio.rentACar.business.response.users.GetUserResponse;
import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.UserCheckService;
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

	
	private ModelMapperService modelMapperService;
	private UserRepository userRepository;
	private UserCheckService userCheckService;
	
	@Autowired
	public UserManager(ModelMapperService modelMapperService, UserRepository userRepository,
			UserCheckService userCheckService) {
		super();
		this.modelMapperService = modelMapperService;
		this.userRepository = userRepository;
		this.userCheckService = userCheckService;
	}

	@Override
	public Result add(CreateUserRequest createUserRequest) throws NumberFormatException, RemoteException {
		//checkPerson(createUserRequest);
		//checkIfUserExists(createUserRequest.getUserNationalIdentity());
		User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
		this.userRepository.save(user);
		return new SuccessResult("USER_ADDED");
	}

	@Override
	public Result delete(DeleteUserRequest deleteUserRequest) {
		User user = this.modelMapperService.forRequest().map(deleteUserRequest, User.class);
		this.userRepository.delete(user);
		return new SuccessResult("USER_DELETED");
	}

	@Override
	public Result update(UpdateUserRequest updateUserRequest) {
		User user = this.modelMapperService.forRequest().map(updateUserRequest, User.class);
		this.userRepository.save(user);
		return new SuccessResult("USER_UPDATED");
	}


	@Override
	public DataResult<List<GetAllUsersResponse>> getAll() {
		List<User> users = this.userRepository.findAll();
		List<GetAllUsersResponse> response = 
				users.stream().map(user -> this.modelMapperService.forResponse()
						.map(user, GetAllUsersResponse.class))
						.collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllUsersResponse>>(response,"ALL_USERS_LISTED");
	}
	
	/*private void checkIfUserExists(String identity) {
		User currentUser = userRepository.getByNationalIdentity(identity);
		if(currentUser!=null) {
			throw new BusinessException("USER_EXISTS");
		}
	}*/

	@Override
	public DataResult<GetUserResponse> getByNationaIdentity(GetUserResponse getUserResponse) {
		return null;
		//return new SuccessDataResult<User>();
		//return new SuccessDataResult<User>(this.userRepository.getByNationalIdentity(getUserResponse.getUserNationalIdentity()),"USER_LISTED");
	}

	@Override
	public DataResult<List<User>> getAll(int pageNo, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		
		return new SuccessDataResult<List<User>>(userRepository.findAll(pageable).getContent(),"USERS_LISTED_PAGEABLE");
	}

	
	public void checkPerson(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NumberFormatException, RemoteException {
		
		if(!userCheckService.checkIndividualCustomer(createIndividualCustomerRequest)) {
			throw new BusinessException("TC_VATANDASI_DEGIL");
		}
			
	}

}
