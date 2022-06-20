package com.kodlamaio.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.InvoiceRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalDetailsRepository;
import com.kodlamaio.rentACar.entities.concretes.Invoice;
import com.kodlamaio.rentACar.entities.concretes.RentalDetails;
@Service
public class InvoiceManager implements InvoiceService{
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private ModelMapperService modelMapperService;
	@Autowired
	private RentalDetailsRepository rentalDetailsRepository;

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		Invoice invoice = modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		RentalDetails rentalDetailsToAddTotalPrice =rentalDetailsRepository.findById(createInvoiceRequest.getRentalDetailsId()).get();
		invoice.setTotalPrice(rentalDetailsToAddTotalPrice.getRental().getTotalPrice());
		invoiceRepository.save(invoice);
		return new SuccessResult("Fatura eklendi");
	}

}
