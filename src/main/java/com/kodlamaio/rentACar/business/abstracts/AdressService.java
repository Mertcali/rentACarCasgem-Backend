package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.adresses.CreateAdressRequest;
import com.kodlamaio.rentACar.business.request.adresses.CreateContactAdressRequest;
import com.kodlamaio.rentACar.business.request.adresses.CreateInvoiceAdressRequest;
import com.kodlamaio.rentACar.business.request.adresses.DeleteAdressRequest;
import com.kodlamaio.rentACar.business.request.adresses.UpdateAdressRequest;
import com.kodlamaio.rentACar.business.response.adresses.GetAdressResponse;
import com.kodlamaio.rentACar.business.response.adresses.GetAllAdressesResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AdressService {

	Result add(CreateAdressRequest createAdressRequest);
	Result addInvoiceAdress(CreateInvoiceAdressRequest createInvoiceAdressRequest);
	Result addContactAdress(CreateContactAdressRequest createContactAdressRequest);
	Result update(UpdateAdressRequest updateAdressRequest);
	Result delete(DeleteAdressRequest deleteAdressRequest);
	DataResult<List<GetAllAdressesResponse>>getAll();
	DataResult<GetAdressResponse>getById(GetAdressResponse getAdressResponse);
}
