package edu.mum.main;


import java.util.List;
import java.util.Set;

import javax.persistence.FetchType;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.domain.Address;
import edu.mum.domain.Member;
import edu.mum.domain.Order;
import edu.mum.service.AddressService;
import edu.mum.service.MemberService;

/*
 * This is the NamedEntitygraph - similar to Join fetch
 * SINGLE Select
 * ...BUT OUTER JOIN...
 * So we get PEAT Moss WITHOUT Addresses

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

    Address address =  new Address();
    address.setCity("Batavia");
    address.setState("Iowa");
  
    Address  address2 =  new Address();
    address2.setCity("Red Rock");
    address2.setState("Iowa");
          
    member.addAddress(address);
    member.addAddress(address2);

    Member member2 = new Member();
    member2.setFirstName("Peat");
    member2.setLastName("Moss");
    member2.setMemberNumber(2);

    Member member3 = new Member();
    member3.setFirstName("Bill");
    member3.setLastName("Due");
    member3.setMemberNumber(3);

    Address address3 =  new Address();
    address3.setCity("Washington");
    address3.setState("Iowa");
 
    Address address4 =  new Address();
    address4.setCity("Mexico");
    address4.setState("Iowa");

    Address address5 =  new Address();
    address5.setCity("Paris");
    address5.setState("Iowa");

    member3.addAddress(address3);
    member3.addAddress(address4);
    member3.addAddress(address5);

     
    memberService.save(member);
    memberService.save(member2);
    memberService.save(member3);
    
    address =  new Address();
    address.setCity("Russia");
    address.setState("Iowa");
    addressService.save(address);
    
   // this is the NamedEntitygraph - similar to Join fetch...BUT OUTER JOIN...
    // So we get PEAT Moss WITHOUT Addresses
   List<Member> memberss = memberService.findByGraph();
   
   for (Member membere : memberss) {


	   
	   System.out.println("Member Name : " + membere.getFirstName() + "  " +  membere.getLastName() );
	
	   for (Address addresse : membere.getAddresses()) {
	       System.out.println("Address : " + addresse.getCity() + 
					"   " + addresse.getState());
	   }
   }

  
  }
  
  }