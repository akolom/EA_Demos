package edu.mum.main;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;

import edu.mum.domain.Address;
import edu.mum.domain.Authority;
import edu.mum.domain.Comment;
import edu.mum.domain.Member;
import edu.mum.domain.UserCredentials;
import edu.mum.security.AuthenticateUser;
import edu.mum.service.AddressService;
import edu.mum.service.CommentService;
import edu.mum.service.MemberService;

/*
 * N+1 issue when Many side is declare EAGER
 * One Fetch of LIST of Members
 * N Fetches of each Members List of Addresses
 */
public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("context/applicationContext.xml");

    AuthenticationManager authenticationManager = (AuthenticationManager) ctx.getBean("authenticationManager");

    AddressService addressService = (AddressService) ctx.getBean("addressServiceImpl");
    MemberService memberService = (MemberService) ctx.getBean("memberServiceImpl");
    CommentService commentService = (CommentService) ctx.getBean("commentServiceImpl");

    Authority authority = new Authority();
    authority.setAuthority("ROLE_USER");
    authority.setUsername("Sean");
    

    UserCredentials userCredentials = new UserCredentials();
    userCredentials.setUsername("Sean");
    userCredentials.setPassword("Sean");
    userCredentials.setEnabled(true);
 
    userCredentials.getAuthority().add(authority);

    Member member = new Member();
    member.setFirstName("Sean");
    member.setLastName("Smith");
    member.setMemberNumber(1);

    member.setUserCredentials(userCredentials);
    
    
    Address address =  new Address();
    address.setCity("Batavia");
    address.setState("Iowa");
  
    Address  address2 =  new Address();
    address2.setCity("Red Rock");
    address2.setState("Iowa");
          
    member.addAddress(address);
    member.addAddress(address2);

    memberService.saveFull(member);

    
    // THIS IS BILL

     authority = new Authority();
    authority.setAuthority("ROLE_ADMIN");
    authority.setUsername("Bill");
    

     userCredentials = new UserCredentials();
    userCredentials.setUsername("Bill");
    userCredentials.setPassword("Bill");
    userCredentials.setEnabled(true);
 
    userCredentials.getAuthority().add(authority);

    
    
     member = new Member();
    member.setFirstName("Bill");
    member.setLastName("DueTimeIsNow");
    member.setMemberNumber(3);

    member.setUserCredentials(userCredentials);
    
    Address address3 =  new Address();
    address3.setCity("Washington");
    address3.setState("Iowa");
 
    Address address4 =  new Address();
    address4.setCity("Mexico");
    address4.setState("Iowa");

     member.addAddress(address3);
    member.addAddress(address4);
     
     memberService.saveFull(member);
  
     // THIS is PETE
     
     authority = new Authority();
    authority.setAuthority("ROLE_USER");
    authority.setUsername("Pete");
    

     userCredentials = new UserCredentials();
    userCredentials.setUsername("Pete");
    userCredentials.setPassword("Pete");
    userCredentials.setEnabled(true);
 
    userCredentials.getAuthority().add(authority);

    
    
     member = new Member();
    member.setFirstName("Pete");
    member.setLastName("Moss");
    member.setMemberNumber(6);

    member.setUserCredentials(userCredentials);
    
    memberService.saveFull(member);
    
    
    System.out.println("Starting authenticateUser:..............   ");
     
 
     AuthenticateUser authenticateUser = new AuthenticateUser();
     try {
  		authenticateUser.authenticate(authenticationManager);
  	} catch (Exception e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
     
     // LOG in as PETE  
     Comment comment = new Comment();
     comment.setMember(member);
     comment.setContent("This is REALLY Good");
     commentService.save(comment);
     
    comment = new Comment();
     comment.setMember(member);
     comment.setContent("This is REALLY Good Too");
     commentService.save(comment);
     
     comment.setContent("This is REALLY Good Too AND EVEN BETTER!");

	 try {
	 commentService.update(comment);
  }
  catch ( AccessDeniedException e) {
 	 System.out.println("****** ACCESS DENIED ! You Need to be OWNER to Update  **********");

  }
 
		 while (true)  {
		 // Now Login in as SEAN - access denied
			    try {
			  		authenticateUser.authenticate(authenticationManager);
			  	} catch (Exception e) {
			  		// TODO Auto-generated catch block
			  		e.printStackTrace();
			  	}
			     
				 try {
					 List<Comment> comments = commentService.findAll();
					 comment = comments.get(0);
				 }
				  catch ( AccessDeniedException e) {
				 	 System.out.println("****** ACCESS DENIED ! You Need to be ROLE_USER to READ  **********");
				
				  }
			 
				 try {
					 commentService.update(comment);
				  }
				  catch ( AccessDeniedException e) {
				 	 System.out.println("****** ACCESS DENIED ! You Need to be OWNER to Update  **********");
				
				  }
		 }
   }
 }