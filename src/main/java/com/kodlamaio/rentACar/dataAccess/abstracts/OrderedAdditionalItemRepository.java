package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entities.concretes.OrderedAdditionalItem;

public interface OrderedAdditionalItemRepository extends JpaRepository<OrderedAdditionalItem, Integer> {

	OrderedAdditionalItem findById(int id);
}
