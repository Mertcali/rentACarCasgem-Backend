package com.kodlamaio.rentACar.entities.concretes;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "corporateCustomerId")
public class CorporateCustomer extends Customer {

	private String taxNumber;
	
	@OneToMany(mappedBy="corporateCustomer")
	private List<Rental> rentals;
}
