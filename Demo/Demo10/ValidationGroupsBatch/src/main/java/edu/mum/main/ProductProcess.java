package edu.mum.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import edu.mum.domain.Authority;
import edu.mum.domain.Item;
import edu.mum.domain.Member;
import edu.mum.domain.Product;
import edu.mum.domain.ProductionStatus;
import edu.mum.domain.UserCredentials;
import edu.mum.security.AuthenticateUser;
import edu.mum.service.CredentialsService;
import edu.mum.service.ProductService;
import edu.mum.service.ItemService;

@Component
public class ProductProcess {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	CredentialsService userCredentialsService;
	
	public void start() throws IOException {
		
		  BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(System.in));
		  
		  System.out.println("******* List of Products AND Status");
	
		 List<Product> products = productService.findAll();
		 for (Product product : products) {
			 System.out.println();
			 System.out.print("Product name: " + product.getName() 
			        + "  Status: " + product.getStatus());
		 }
		  
		/*  List<Item>items=itemService.findAll();
		  for(Item item: items){
			  System.out.println();
			  System.out.println("Item name: " + item.getName()+" Status: "+ item.getDescription());
		  }*/
		  
		 System.out.println();
		 
 	   AuthenticateUser authenticateUser = new AuthenticateUser();
	
			 while (true)  {
				    try {
				  		authenticateUser.authenticate(authenticationManager);
				  		
				  	} catch (Exception e) {
				  		// TODO Auto-generated catch block
				  		e.printStackTrace();
				  	}
			     try {
			    	    String userName =  SecurityContextHolder.getContext().getAuthentication().getName();
			    	    UserCredentials userCredentials = userCredentialsService.findByUserName(userName);
			    	    Authority authority = userCredentials.getAuthority().get(0);
			    	    Member member = userCredentials.getMember();
			    	    
	
			    	    if (member.getFirstName().equals("Sean")  || member.getFirstName().equals("Paul"))
			    	    {
			    	    	Product product = 	 productService.findOne(1L);
							if (checkValidStatusForUser(authority,product))
								productService.update(product);
						     
						     product = 	 productService.findOne(3L);
					    	 if (checkValidStatusForUser(authority,product))
					    		 productService.update(product);
			    	    }
			    	    
	
			    	    if (member.getFirstName().equals("Bill")  || member.getFirstName().equals("Pete"))
			    	    {
					     Product product = 	 productService.findOne(2L);
				    	 if (checkValidStatusForUser(authority,product))
				    		 productService.update(product);
	
					     product = 	 productService.findOne(4L);
				    	 if (checkValidStatusForUser(authority,product))
				    		 productService.update(product);
			    	    }
	}
				  catch ( AccessDeniedException e) {
				 	 System.out.println("****** ACCESS DENIED ! You Need to have Proper ACL to Access!  **********");
				
				  }
	
				 System.out.println("******* List of Products AND Status");
	
				 products = productService.findAll();
				 for (Product product : products) {
					 System.out.println();
					 System.out.print("Product name: " + product.getName() 
					        + "  Status: " + product.getStatus());
				 }
				 System.out.println();
				 System.out.println("Tye X to exit, C to Continue:");
				 String status = inputBuffer.readLine();
				 if (status.startsWith("X"))
					 break;
				 
			}
			 
	  }
	 
	 private static boolean checkValidStatusForUser(Authority authority, Product product) {
		 if ( (authority.getAuthority().equals("ROLE_ADMIN") 
				 && product.getStatus().equals(ProductionStatus.BASIC) )
				 ||
				 (authority.getAuthority().equals("ROLE_SUPERVISOR") 
		    			 && product.getStatus().equals(ProductionStatus.DETAILS)) )
			 { return true; } 
		 		else return false;
	 }
 
}
