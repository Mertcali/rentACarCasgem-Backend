package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AdressService;
import com.kodlamaio.rentACar.business.request.adresses.CreateAdressRequest;
import com.kodlamaio.rentACar.business.request.adresses.DeleteAdressRequest;
import com.kodlamaio.rentACar.business.request.adresses.UpdateAdressRequest;
import com.kodlamaio.rentACar.business.response.adresses.GetAdressResponse;
import com.kodlamaio.rentACar.business.response.adresses.GetAllAdressesResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/addresses")
public class AdressController {

	@Autowired
	private AdressService adressService;
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateAdressRequest createAdressRequest)
	{
		return this.adressService.add(createAdressRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteAdressRequest deleteAdressRequest)
	{
		return this.adressService.delete(deleteAdressRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateAdressRequest updateAdressRequest) {
		return this.adressService.update(updateAdressRequest);
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetAdressResponse> getById(@RequestBody GetAdressResponse getAdressResponse)
	{
		return this.adressService.getById(getAdressResponse);
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllAdressesResponse>> getAll(){
		return this.adressService.getAll();
	}
}
