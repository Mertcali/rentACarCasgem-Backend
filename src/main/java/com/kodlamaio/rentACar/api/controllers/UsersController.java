package com.kodlamaio.rentACar.api.controllers;

import java.rmi.RemoteException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.UserService;
import com.kodlamaio.rentACar.business.request.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.request.users.DeleteUserRequest;
import com.kodlamaio.rentACar.business.request.users.UpdateUserRequest;
import com.kodlamaio.rentACar.business.response.users.GetAllUsersResponse;
import com.kodlamaio.rentACar.business.response.users.GetUserResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.User;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateUserRequest createUserRequest) throws NumberFormatException, RemoteException {
		return this.userService.add(createUserRequest);

	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteUserRequest deleteUserRequest) {
		return this.userService.delete(deleteUserRequest);
	
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
		return this.userService.update(updateUserRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllUsersResponse>> getAll(){
		return this.userService.getAll();
	}
	
	@GetMapping("/getAllByPage")
	public DataResult <List<User>> getAll(int pageNo,int pageSize){
		return this.userService.getAll(pageNo, pageSize);
	}
	
	@GetMapping("/getByNationaIdentity")
	public DataResult<GetUserResponse> getByNationaIdentity(@RequestParam GetUserResponse getUserResponse){
		return userService.getByNationaIdentity(getUserResponse);
	}
}
