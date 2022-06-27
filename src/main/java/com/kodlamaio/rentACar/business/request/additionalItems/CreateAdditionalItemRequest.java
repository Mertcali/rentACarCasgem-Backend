package com.kodlamaio.rentACar.business.request.additionalItems;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdditionalItemRequest {
	
	@NotNull
	@Size(min=1,max=20)
	private String description;
	@NotEmpty
	@Size(min=1,max=20)
	private String name;
	@Min(value=10)
	private double dailyPrice;
}
