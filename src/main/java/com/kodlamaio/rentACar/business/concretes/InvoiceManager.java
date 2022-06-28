package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.request.invoices.DeleteInvoiceRequest;
import com.kodlamaio.rentACar.business.request.invoices.UpdateInvoiceRequest;
import com.kodlamaio.rentACar.business.response.invoices.GetAllInvoicesResponse;
import com.kodlamaio.rentACar.business.response.invoices.GetInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.InvoiceRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.Invoice;
import com.kodlamaio.rentACar.entities.concretes.Rental;
@Service
public class InvoiceManager implements InvoiceService{
	
	
	private InvoiceRepository invoiceRepository;
	private ModelMapperService modelMapperService;
	private RentalRepository rentalRepository;

	@Autowired
	public InvoiceManager( InvoiceRepository invoiceRepository, ModelMapperService modelMapperService,
			RentalRepository rentalRepository) {
		super();
		this.invoiceRepository = invoiceRepository;
		this.modelMapperService = modelMapperService;
		this.rentalRepository = rentalRepository;
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {

		checkIfInvoiceExistsByNumber(createInvoiceRequest.getInvoiceNumber());
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		//RentalDetail rentalDetail=this.rentalDetailRepository.findById(createInvoiceRequest.getRentalDetailId());
		Rental rental = this.rentalRepository.findById(createInvoiceRequest.getRentalId());
		invoice.setInvoiceNumber(createInvoiceNumber());
		invoice.setRental(rental);
		invoice.setState(1);
	    //STATE-0 --> IPTAL EDILMIS FATURA
		//STATE-1 --> AKTİF FATURA
		
		// IND VE CORP İÇİN AYRILMALI
		System.out.println(rental.getId());
		invoiceRepository.save(invoice);
	    
		return  new SuccessResult("INVOICE_ADDED");
	
	}
	

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		Invoice invoice = this.invoiceRepository.findById(updateInvoiceRequest.getId());
		invoice.setState(0);
		Invoice invoiceToUpdate = this.modelMapperService.forRequest().map(updateInvoiceRequest,
				Invoice.class);
		invoiceToUpdate.setId(invoiceToUpdate.getId()+1);
		invoiceToUpdate.setState(1);
		this.invoiceRepository.save(invoiceToUpdate);
		return new SuccessResult("INVOICE_UPDATED");
	}
	
	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		Invoice invoice = this.modelMapperService.forRequest().map(deleteInvoiceRequest, Invoice.class);
		this.invoiceRepository.delete(invoice);
		return new SuccessResult("INVOICE_DELETED");
	}

	@Override
	public DataResult<GetInvoiceResponse> getById(GetInvoiceResponse getInvoiceResponse) {
		Invoice invoice = this.invoiceRepository.findById(getInvoiceResponse.getId());
		GetInvoiceResponse response = this.modelMapperService.forResponse().map(invoice, GetInvoiceResponse.class);
		return new SuccessDataResult<GetInvoiceResponse>(response,"INVOICE_LISTED");
	}

	@Override
	public DataResult<List<GetAllInvoicesResponse>> getAll() {
		List<Invoice> invoices = this.invoiceRepository.findAll();
		List<GetAllInvoicesResponse> response = invoices.stream().map(invoice -> this.modelMapperService.forResponse().map(invoice, GetAllInvoicesResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllInvoicesResponse>>(response,"ALL_INVOICES_LISTED");
	}
	
	private void checkIfInvoiceExistsByNumber(String invoiceNumber) {
		Invoice currentInvoice = invoiceRepository.findByInvoiceNumber(invoiceNumber);
		if(currentInvoice!=null) {
			throw new BusinessException("INVOICE_EXISTS");
		}
	}

	private String createInvoiceNumber() {
		Random random = new Random();
		int sayi = random.nextInt(999999)+1000000;
		String stringSayi = String.valueOf(sayi);
		return stringSayi;
 }
}
