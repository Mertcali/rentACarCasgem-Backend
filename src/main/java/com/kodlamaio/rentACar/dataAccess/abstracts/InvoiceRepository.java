package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlamaio.rentACar.entities.concretes.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{

	Invoice findById(int id);
	Invoice findByInvoiceNumber(String invoiceNumber);
}
