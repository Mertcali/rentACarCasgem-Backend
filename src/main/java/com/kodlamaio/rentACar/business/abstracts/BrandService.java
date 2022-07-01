package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.business.request.brands.DeleteBrandRequest;
import com.kodlamaio.rentACar.business.request.brands.UpdateBrandRequest;
import com.kodlamaio.rentACar.business.response.brands.GetAllBrandsResponse;
import com.kodlamaio.rentACar.business.response.brands.GetBrandResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Brand;

public interface BrandService {
	// Response request pattern
	Result add(CreateBrandRequest createBrandRequest);
	
	Result addWithBuilder(CreateBrandRequest createBrandRequest);

	Result update(UpdateBrandRequest updateBrandRequest);
	
	Result updateWithBuilder(UpdateBrandRequest updateBrandRequest);

	Result delete(DeleteBrandRequest deleteBrandRequest);
	
	Result deleteWithBuilder(DeleteBrandRequest deleteBrandRequest);
	
	DataResult<GetBrandResponse> getById(GetBrandResponse getBrandResponse);
	
	DataResult<GetBrandResponse> getByIdWithBuilder(GetBrandResponse getBrandResponse);

	DataResult<List<GetAllBrandsResponse>> getAll();
	
	DataResult<List<GetAllBrandsResponse>> getAllWithBuilder();
	

}
