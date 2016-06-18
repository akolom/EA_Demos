package edu.mum.main;


import java.util.List;

import org.hibernate.LazyInitializationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.domain.Authority;
import edu.mum.domain.Member;
import edu.mum.domain.UserCredentials;
import edu.mum.service.MemberService;

public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");

    MemberService memberService = (MemberService) ctx.getBean("memberServiceImpl");
 
    Authority authority = new Authority();
    authority.setAuthority("USER");
    authority.setUsername("JohnDoe");
    

    UserCredentials userCredentials = new UserCredentials();
    userCredentials.setUsername("JohnDoe");
    userCredentials.setPassword("DoeNuts");
 
    userCredentials.getAuthority().add(authority);

    
    Member member = new Member();
    member.setFirstName("Sean");
    member.setLastName("Smith");
    member.setMemberNumber(1);

    member.setUserCredentials(userCredentials);

    // Automatically cascades Persist to UserCredentials
    memberService.save(member);

    member = memberService.findOneFull(member.getId());
       
    System.out.println("*************  Access Member & Credentials - even though LAZY loaded  ********");
    System.out.println("Member Name : " + member.getFirstName() + "  " +  member.getLastName() );
    System.out.println("UserCredentials Name : " + userCredentials.getUsername() + 
			"  Password: " + userCredentials.getPassword());
    System.out.println();

    // Demonstrate Lazy load issue
    member = memberService.findOne(member.getId());
    
    try {
    	member.getUserCredentials().getUsername();
    }
    catch (LazyInitializationException e) {
        System.out.println("*************  Access Member TRY to access Credentials *******");
     	     System.out.println("EXCEPTION : UserCredentials Unavailable - LAZY loaded");
     	    System.out.println();
  }
    
    // Demonstrate Dynamic Criteria API Query
    List<Member> members = memberService.findMemberCriteria(0, "se", "sm");
    System.out.println("*************  Criteria API Example *******");

    for (Member memberr : members) {
        System.out.println("Member Name : " + memberr.getFirstName() + "  " +  memberr.getLastName() );

    }
	    System.out.println();

	    
    // Demonstrate DELETE 
    memberService.delete(member);
    

  }
  
  
  }