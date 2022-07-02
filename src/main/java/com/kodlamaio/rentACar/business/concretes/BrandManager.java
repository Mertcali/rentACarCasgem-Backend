	package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.BrandService;
import com.kodlamaio.rentACar.business.request.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.business.request.brands.DeleteBrandRequest;
import com.kodlamaio.rentACar.business.request.brands.UpdateBrandRequest;
import com.kodlamaio.rentACar.business.response.brands.GetAllBrandsResponse;
import com.kodlamaio.rentACar.business.response.brands.GetBrandResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.BrandRepository;
import com.kodlamaio.rentACar.entities.concretes.Brand;

//BrandServiceImpl
@Service
public class BrandManager implements BrandService {

	
	
	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public BrandManager(BrandRepository brandRepository, ModelMapperService modelMapperService) {
		super();
		this.brandRepository = brandRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		checkIfBrandExistsByName(createBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND_ADDED");

	}
	
	@Override
	public Result addWithBuilder(CreateBrandRequest createBrandRequest) {
		Brand brand = Brand.builder().name(createBrandRequest.getName()).build();
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND_ADDED_WITH_LOMBOK");
	}


	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND_UPDATED");

	}

	@Override
	public Result updateWithBuilder(UpdateBrandRequest updateBrandRequest) {
		Brand brand = Brand.builder().id(updateBrandRequest.getId())
				.name(updateBrandRequest.getName()).build();
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND_UPDATED_WITH_LOMBOK");
	}
	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {

		Brand brand = this.modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		
		this.brandRepository.delete(brand);
		return new SuccessResult("BRAND_DELETED");

	}
	
	@Override
	public Result deleteWithBuilder(DeleteBrandRequest deleteBrandRequest) {
		Brand brand = Brand.builder().id(deleteBrandRequest.getId()).build();
		this.brandRepository.delete(brand);
		return new SuccessResult("BRAND_DELETED_WITH_LOMBOK");
	}

	@Override
	public DataResult<GetBrandResponse> getById(GetBrandResponse getBrandResponse) {
		Brand brand = this.brandRepository.findById(getBrandResponse.getId());
		GetBrandResponse response = this.modelMapperService.forResponse().map(brand,GetBrandResponse.class);
		return new SuccessDataResult<GetBrandResponse>(response,"BRAND_LISTED");
	}
	
	@Override
	public DataResult<GetBrandResponse> getByIdWithBuilder(GetBrandResponse getBrandResponse) {
		Brand brand = this.brandRepository.findById(getBrandResponse.getId());
		GetBrandResponse response = GetBrandResponse.builder()
				.id(brand.getId())
				.name(brand.getName()).build();
		return new SuccessDataResult<GetBrandResponse>(response,"BRAND_LISTED_WITH_LOMBOK");
	}

	@Override
	public DataResult<List<GetAllBrandsResponse>>getAll() {
		 List<Brand>brands = this.brandRepository.findAll();
		    List<GetAllBrandsResponse>response =
		            brands.stream().map(brand->this.modelMapperService.forResponse()
		                    .map(brand, GetAllBrandsResponse.class))
		            		.collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllBrandsResponse>>(response,"ALL_BRANDS_LISTED");
	}

	@Override
	public DataResult<List<GetAllBrandsResponse>> getAllWithBuilder() {
		List<Brand> brands =this.brandRepository.findAll();
	//
		return new DataResult<List<GetAllBrandsResponse>>(null, false);
	}
	
	private void checkIfBrandExistsByName(String name) {
		Brand currentBrand = brandRepository.findByName(name);
		if(currentBrand!=null) {
			throw new BusinessException("BRAND_EXISTS");
		}
	}











	
}
