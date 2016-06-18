package edu.mum.main;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.domain.Member;
import edu.mum.domain.UserCredentials;
import edu.mum.service.MemberService;
import edu.mum.service.UserCredentialsService;

public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");

    UserCredentialsService userCredentialsService = 
    		(UserCredentialsService) ctx.getBean("userCredentialsServiceImpl");
//MemberService memberService = (MemberService)ctx.getBean("memberServiceImpl");
    Member member = new Member();
    member.setFirstName("Sean");
    member.setLastName("Smith");
    member.setMemberNumber(1);
    
    UserCredentials userCredentials = new UserCredentials();
    userCredentials.setUsername("JohnDoe");
    userCredentials.setPassword("DoeNuts");

    // Set both sides
    userCredentials.setMember(member);
    // If we do NOT set the user credentials in member THEN 
    // member [owning side] will be saved but will NOT have FK  
    // COMMENT out this line to see:
    member.setUserCredentials(userCredentials);
    
    // Cascade to Member
    userCredentialsService.save(userCredentials);
    
    userCredentials = userCredentialsService.findByUserName(userCredentials.getUsername());
//member = memberService.findByMemberNumber(member.getMemberNumber());

	    System.out.println();

	    System.out.println("UserCredentials Name : " + userCredentials.getUsername() + 
	    						"  Password: " + userCredentials.getPassword());
	     // Access MEMBER from Credentials
	    System.out.println("Member Name: " + userCredentials.getMember().getFirstName() + " " + userCredentials.getMember().getLastName());
	    System.out.println();

  }
  
  
  }