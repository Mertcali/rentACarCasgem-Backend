package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.ColorService;
import com.kodlamaio.rentACar.business.request.colors.CreateColorRequest;
import com.kodlamaio.rentACar.business.request.colors.DeleteColorRequest;
import com.kodlamaio.rentACar.business.request.colors.UpdateColorRequest;
import com.kodlamaio.rentACar.business.response.colors.GetAllColorsResponse;
import com.kodlamaio.rentACar.business.response.colors.GetColorResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentACar.entities.concretes.Brand;
import com.kodlamaio.rentACar.entities.concretes.Color;

import net.bytebuddy.asm.Advice.This;

@Service
public class ColorManager implements ColorService {

	
	private ColorRepository colorRepository;
	private ModelMapperService modelMapperService;

	@Autowired
	public ColorManager(ColorRepository colorRepository, ModelMapperService modelMapperService) {
		super();
		this.colorRepository = colorRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {
		checkIfColorExistsByName(createColorRequest.getName());
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorRepository.save(color);
		
		return new SuccessResult("ADDED_COLOR");

	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		/*Color color = new Color();
		color.setId(updateColorRequest.getId());
		color.setName(updateColorRequest.getName());*/ //mapping yokken böyleydi	
		Color color = modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorRepository.save(color);
		return new SuccessResult("UPDATED_COLOR");
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		Color color = modelMapperService.forRequest().map(deleteColorRequest, Color.class);
		colorRepository.delete(color);
		return new SuccessResult("DELETED_COLOR");
	}

	@Override
	public DataResult<GetColorResponse> getById(GetColorResponse getColorResponse) {
		Color color = colorRepository.findById(getColorResponse.getId()); 
		GetColorResponse response = modelMapperService.forResponse().map(color, GetColorResponse.class);
		return new SuccessDataResult<GetColorResponse>(response,"COLOR_LISTED");
		//return new SuccessDataResult<Color>(this.colorRepository.getById(getColorResponse.getId()));
	}

	@Override
	public DataResult<List<GetAllColorsResponse>> getAll() {
		List<Color> colors = this.colorRepository.findAll();
		List<GetAllColorsResponse> response = colors.stream()
				.map(color -> this.modelMapperService.forResponse()
						.map(color, GetAllColorsResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllColorsResponse>>(response,"ALL_COLORS_LISTED");
		//return new SuccessDataResult<List<Color>>( this.colorRepository.findAll());
	}

	private void checkIfColorExistsByName(String name) {
		Color currentColor = colorRepository.findByName(name);
		if(currentColor!=null) {
			throw new BusinessException("COLOR_EXISTS");
		}
	}
}
