package edu.mum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import edu.mum.service.AccountDetailsService;
import edu.mum.wsdl.account.Account;
import edu.mum.wsdl.account.AccountDetailsRequest;
import edu.mum.wsdl.account.AccountDetailsResponse;
import edu.mum.wsdl.account.ObjectFactory;

@Service
	public class AccountDetailsServiceImpl implements AccountDetailsService {
	    @Autowired
	    private WebServiceTemplate webServiceTemplate;
	 
	    public Account getAccount(Account account) {
	    	AccountDetailsRequest accountDetailsRequest = new ObjectFactory().createAccountDetailsRequest();
	    	accountDetailsRequest.setAccountNumber(account.getAccountNumber());
 	 
	        AccountDetailsResponse response = (AccountDetailsResponse) webServiceTemplate.marshalSendAndReceive(
	        		accountDetailsRequest);
	 
	        return response.getAccountDetails();
	    }
	}

