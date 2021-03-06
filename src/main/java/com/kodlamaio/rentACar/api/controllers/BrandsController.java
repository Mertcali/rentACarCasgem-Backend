package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.BrandService;
import com.kodlamaio.rentACar.business.request.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.business.request.brands.DeleteBrandRequest;
import com.kodlamaio.rentACar.business.request.brands.UpdateBrandRequest;
import com.kodlamaio.rentACar.business.response.brands.GetAllBrandsResponse;
import com.kodlamaio.rentACar.business.response.brands.GetBrandResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Brand;

@RestController
@RequestMapping("/api/brands" )
public class BrandsController {

	@Autowired
	private BrandService brandService;

	//endpoint
	@PostMapping("/add")
	public Result add(@RequestBody CreateBrandRequest createBrandRequest) {
		return this.brandService.add(createBrandRequest);
	}
	
	@PostMapping("/addWithBuilder")
	public Result addWithBuilder(@RequestBody CreateBrandRequest createBrandRequest) {
		return this.brandService.addWithBuilder(createBrandRequest);
	}

	
	@GetMapping("/getbyid")
	public DataResult<GetBrandResponse> getById(@RequestBody GetBrandResponse getBrandResponse) {
		return brandService.getById(getBrandResponse);		
	}
	
	@GetMapping("/getbyidwithbuilder")
	public DataResult<GetBrandResponse> getByIdWithBuilder(@RequestBody GetBrandResponse getBrandResponse) {
		return brandService.getByIdWithBuilder(getBrandResponse);		
	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateBrandRequest updateBrandRequest) {
		return this.brandService.update(updateBrandRequest);
	}
	
	@PostMapping("/updatewithbuilder")
	public Result updateWithBuilder(@RequestBody UpdateBrandRequest updateBrandRequest) {
		return this.brandService.updateWithBuilder(updateBrandRequest);
	}
	

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteBrandRequest deleteBrandRequest) {
		return this.brandService.delete(deleteBrandRequest);
	}
	
	@PostMapping("/deletewithbuilder")
	public Result deleteWithBuilder(@RequestBody DeleteBrandRequest deleteBrandRequest) {
		return this.brandService.deleteWithBuilder(deleteBrandRequest);
	}

	@GetMapping("/getall")
	public DataResult<List<GetAllBrandsResponse>> getAll() {
		return this.brandService.getAll();
	}
	
	@GetMapping("/getallwithbuilder")
	public DataResult<List<GetAllBrandsResponse>> getAllWithBuilder() {
		return this.brandService.getAllWithBuilder();
	}
	
	@RequestMapping(path = "/getAll",method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public DataResult<List<GetAllBrandsResponse>> getAll2() {
		return this.brandService.getAll();
	}
}
