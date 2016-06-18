package edu.mum.main;

 import edu.mum.service.AccountDetailsService;
import edu.mum.wsdl.account.Account;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;




public class Application {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");
		AccountDetailsService accountDetailsService = (AccountDetailsService) context.getBean("accountDetailsServiceImpl");
		
		 Account account = new  Account(); 
		account.setAccountName("Pete Moss");
		account.setAccountNumber("12345");
		Account accountResult = accountDetailsService.getAccount(account);
		System.out.println("Receiving result: " + accountResult.getAccountName());
	}

}
