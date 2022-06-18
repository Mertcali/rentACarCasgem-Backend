package com.kodlamaio.rentACar.core.utilities.adapters.concretes;

import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.request.users.CreateUserRequest;
import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.UserCheckService;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisKpsAdapter implements UserCheckService{

	@Override
	public boolean checkUser(CreateUserRequest createUserRequest) throws NumberFormatException, RemoteException {
		KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();
		
		boolean result = kpsPublicSoapProxy.TCKimlikNoDogrula
				(Long.parseLong(createUserRequest.getUserNationalIdentity()), 
						createUserRequest.getUserFirstName(), 
						createUserRequest.getUserLastName(), 
						createUserRequest.getBirthYear());
		return result;
	
	}
	
	

}
