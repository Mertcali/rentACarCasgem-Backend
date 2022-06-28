package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import org.springframework.context.annotation.Lazy;

import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.request.invoices.DeleteInvoiceRequest;
import com.kodlamaio.rentACar.business.request.invoices.UpdateInvoiceRequest;
import com.kodlamaio.rentACar.business.request.invoices.forCorporateCustomers.CreateInvoiceForCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.request.invoices.forIndividualCustomers.CreateInvoiceForIndividualCustomerRequest;import com.kodlamaio.rentACar.business.response.individualCustomers.GetAllIndividualCustomersResponse;
import com.kodlamaio.rentACar.business.response.invoices.GetAllInvoicesResponse;
import com.kodlamaio.rentACar.business.response.invoices.GetInvoiceResponse;
import com.kodlamaio.rentACar.business.response.invoices.forCorporateCustomers.GetAllInvoicesForCorporateCustomersResponse;
import com.kodlamaio.rentACar.business.response.invoices.forCorporateCustomers.GetInvoiceForCorporateCustomerResponse;
import com.kodlamaio.rentACar.business.response.invoices.forIndividualCustomers.GetAllInvoicesForIndividualCustomersResponse;
import com.kodlamaio.rentACar.business.response.invoices.forIndividualCustomers.GetInvoiceForIndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface InvoiceService {
	
	Result add(CreateInvoiceRequest createInvoiceRequest);
	Result addInvoiceForIndividualCustomer(CreateInvoiceForIndividualCustomerRequest createInvoiceForIndividualCustomerRequest);
	Result addInvoiceForCorporateCustomer(CreateInvoiceForCorporateCustomerRequest createInvoiceForCorporateCustomerRequest);
	
	Result update(UpdateInvoiceRequest updateInvoiceRequest);
	Result delete(DeleteInvoiceRequest deleteInvoiceRequest);
	
	DataResult<GetInvoiceForCorporateCustomerResponse> getByCorporateCustomerId(GetInvoiceForCorporateCustomerResponse getInvoiceForCorporateCustomerResponse);
	DataResult<GetInvoiceForIndividualCustomerResponse> getByIndividualCustomerId(GetInvoiceForIndividualCustomerResponse getInvoiceForIndividualCustomerResponse);
	DataResult<GetInvoiceResponse>getById(GetInvoiceResponse getInvoiceResponse);
	
	DataResult<List<GetAllInvoicesForIndividualCustomersResponse>> getAllForIndividualCustomers();
	DataResult<List<GetAllInvoicesForCorporateCustomersResponse>> getallForCorporateCustomers();
	DataResult<List<GetAllInvoicesResponse>> getAll();

}
