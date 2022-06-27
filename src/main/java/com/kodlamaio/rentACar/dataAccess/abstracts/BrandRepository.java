package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.business.request.brands.DeleteBrandRequest;
import com.kodlamaio.rentACar.entities.concretes.Brand;

//JpaRepository: Repository Pattern
public interface BrandRepository extends JpaRepository<Brand, Integer> {
	Brand findByName(String name);
	Brand findById(int id);

}
