package com.kodlamaio.rentACar.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
@RestController
@RequestMapping("&api/invoices")
public class InvoicesController {

	private InvoiceService invoiceService;

	@PostMapping("/add")
	public Result add(@RequestBody CreateInvoiceRequest createInvoiceRequest) {
		var result = invoiceService.add(createInvoiceRequest);
		if (result.isSuccess()) {
			return new SuccessResult(result.getMessage());
		} else {
			return new ErrorResult(result.getMessage());
		}
	}
}
