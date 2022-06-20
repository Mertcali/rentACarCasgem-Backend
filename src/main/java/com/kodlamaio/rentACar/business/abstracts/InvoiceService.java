package com.kodlamaio.rentACar.business.abstracts;

import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface InvoiceService {
	
	Result add(CreateInvoiceRequest createInvoiceRequest);

}
