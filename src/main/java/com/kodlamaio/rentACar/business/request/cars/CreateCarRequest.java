package com.kodlamaio.rentACar.business.request.cars;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	
	@Size(min=2,max=20)
	private String description;
	@Min(value = 100)
	private double dailyPrice;
	private Integer brandId;
	private Integer colorId;
	@Size(min=7,max=8)
	private String plate;
	private Integer kilometer;
	private Integer minFindexScore;

}
