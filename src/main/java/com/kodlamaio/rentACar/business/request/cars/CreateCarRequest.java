package com.kodlamaio.rentACar.business.request.cars;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	
	@NotBlank
	@NotNull
	@Size(min=2,max=20)
	private String description;
	private double dailyPrice;
	private int brandId;
	private int colorId;
	private String plate;
	private Integer kilometer;
	private Integer minFindexScore;

}
