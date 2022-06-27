package com.kodlamaio.rentACar.core.utilities.adapters.concretes;

import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.request.individualCustomers.CreateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.request.users.CreateUserRequest;
import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.UserCheckService;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisKpsAdapter implements UserCheckService{

	@Override
	public boolean checkIndividualCustomer(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NumberFormatException, RemoteException {
		KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();
		
		boolean result = kpsPublicSoapProxy.TCKimlikNoDogrula
				(Long.parseLong(createIndividualCustomerRequest.getNationalIdentity()), 
						createIndividualCustomerRequest.getFirstName(), 
						createIndividualCustomerRequest.getLastName(), 
						createIndividualCustomerRequest.getBirthYear());
		return result;
	
	}
	
	

}
