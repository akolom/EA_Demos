package edu.mum.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.domain.Member;
import edu.mum.service.AddressService;
import edu.mum.service.MemberService;

/*
 * The is A specific Join Fetch 
 * Declared in memberService.findAllJoinFetch()
 * Demonstrates the Cartesian Product Problem
 */
		
public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");

    AddressService addressService = (AddressService) ctx.getBean("addressServiceImpl");
    MemberService memberService = (MemberService) ctx.getBean("memberServiceImpl");

    
 
  
    Member member = new Member();
    member.setFirstName("Sean");
    member.setLastName("Smith");
    member.setMemberNumber(1);
   
    memberService.save(member);
     
 
    Member findMember = memberService.findByNamedQuery(member.getMemberNumber());
    System.out.println();
    System.out.println("    Find by Named Query");
  	System.out.println("Member Name : " + findMember.getFirstName() + "  " +  findMember.getLastName() );

  	findMember = memberService.findByNativeQuery(member.getMemberNumber());
  	System.out.println();
  	System.out.println("                      Find by Native Query");
  	System.out.println("Member Name : " + findMember.getFirstName() + "  " +  findMember.getLastName() );

  	
  	
    }

 
  
  }