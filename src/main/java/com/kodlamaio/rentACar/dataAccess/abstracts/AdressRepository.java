package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entities.concretes.Adress;

public interface AdressRepository extends JpaRepository<Adress, Integer>{

	Adress findById(int id);
}
