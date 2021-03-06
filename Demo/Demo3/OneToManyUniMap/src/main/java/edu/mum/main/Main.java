package edu.mum.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.domain.Authority;
import edu.mum.domain.UserCredentials;
import edu.mum.service.UserCredentialsService;

public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");

    UserCredentialsService userCredentialsService = (UserCredentialsService) ctx.getBean("userCredentialsServiceImpl");

    Authority authority = new Authority();
    authority.setAuthority("ROLE_USER");
    authority.setUsername("JohnDoe");
    
    Authority authority2 = new Authority();
    authority2.setAuthority("ROLE_ADMIN");
    authority2.setUsername("JohnDoe");
    
    Authority authority3 = new Authority();
    authority3.setAuthority("ROLE_SUPERVISOR");
    authority3.setUsername("JohnDoe");
    
    
    
    UserCredentials userCredentials = new UserCredentials();
    userCredentials.setUsername("JohnDoe");
    userCredentials.setPassword("DoeNuts");
    
    userCredentials.getAuthority().put("user",authority);
    userCredentials.getAuthority().put("admin",authority2);
    userCredentials.getAuthority().put("supervisor",authority3);

    userCredentialsService.save(userCredentials);
 
    userCredentials = userCredentialsService.findByUserName(userCredentials.getUsername());

	    System.out.println();
 	    System.out.println("******************   Display UserCredentials  *********");

    System.out.println("UserCredentials Name : " + userCredentials.getUsername() + 
    						"  Password: " + userCredentials.getPassword());
/*	    System.out.println("******************   Display Authorities  *********");
  
    for (Authority authorite : userCredentials.getAuthority()) {
        System.out.println("Authority User Name : " + authorite.getUsername() + 
				"  Authority: " + authorite.getAuthority());
    }
*/ 
  }
}
   