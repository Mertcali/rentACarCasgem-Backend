package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.request.invoices.DeleteInvoiceRequest;
import com.kodlamaio.rentACar.business.request.invoices.UpdateInvoiceRequest;
import com.kodlamaio.rentACar.business.request.invoices.forCorporateCustomers.CreateInvoiceForCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.request.invoices.forIndividualCustomers.CreateInvoiceForIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.response.invoices.GetAllInvoicesResponse;
import com.kodlamaio.rentACar.business.response.invoices.GetInvoiceResponse;
import com.kodlamaio.rentACar.business.response.invoices.forCorporateCustomers.GetAllInvoicesForCorporateCustomersResponse;
import com.kodlamaio.rentACar.business.response.invoices.forCorporateCustomers.GetInvoiceForCorporateCustomerResponse;
import com.kodlamaio.rentACar.business.response.invoices.forIndividualCustomers.GetAllInvoicesForIndividualCustomersResponse;
import com.kodlamaio.rentACar.business.response.invoices.forIndividualCustomers.GetInvoiceForIndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {

	@Autowired
	private  InvoiceService invoiceService;

	@PostMapping("/add")
	public Result add(@RequestBody CreateInvoiceRequest createInvoiceRequest) {
		return this.invoiceService.add(createInvoiceRequest);
	}
	
	@PostMapping("/add/forindividualcustomer")
	public Result add(@RequestBody CreateInvoiceForIndividualCustomerRequest createInvoiceForIndividualCustomerRequest) {
		return this.invoiceService.addInvoiceForIndividualCustomer(createInvoiceForIndividualCustomerRequest);
	}
	
	@PostMapping("/add/forcorporatecustomer")
	public Result add(@RequestBody CreateInvoiceForCorporateCustomerRequest createInvoiceForCorporateCustomerRequest) {
		return this.invoiceService.addInvoiceForCorporateCustomer(createInvoiceForCorporateCustomerRequest);
	}
	
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateInvoiceRequest updateInvoiceRequest) {
		return this.invoiceService.update(updateInvoiceRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteInvoiceRequest deleteInvoiceRequest) {
		return this.invoiceService.delete(deleteInvoiceRequest);
	}
	
	@GetMapping("/getby/id")
	public DataResult<GetInvoiceResponse> getById(@RequestBody GetInvoiceResponse getInvoiceResponse){
		return this.invoiceService.getById(getInvoiceResponse);
	}
	
	@GetMapping("/getby/corporatecustomerid")
	public DataResult<GetInvoiceForCorporateCustomerResponse> getByCorporateCustomerId(@RequestBody GetInvoiceForCorporateCustomerResponse getInvoiceForCorporateCustomerResponse){
		return this.invoiceService.getByCorporateCustomerId(getInvoiceForCorporateCustomerResponse);
	}
	
	@GetMapping("/getby/individualcustomerid")
	public DataResult<GetInvoiceForIndividualCustomerResponse>
	getByIndividualCustomerId(@RequestBody GetInvoiceForIndividualCustomerResponse getInvoiceForIndividualCustomerResponse){
		return this.invoiceService.getByIndividualCustomerId(getInvoiceForIndividualCustomerResponse);
	}
	@GetMapping("/getall")
	public DataResult<List<GetAllInvoicesResponse>> getAll(){
		return this.invoiceService.getAll();
	}
	
	@GetMapping("/getall/forindividualcustomers")
	public DataResult<List<GetAllInvoicesForIndividualCustomersResponse>> getAllForIndividualCustomers(){
		return this.invoiceService.getAllForIndividualCustomers();
	}
	
	@GetMapping("/getall/forcorporatecustomers")
	public DataResult<List<GetAllInvoicesForCorporateCustomersResponse>> getallForCorporateCustomers(){
		return this.invoiceService.getallForCorporateCustomers();
	}
}
