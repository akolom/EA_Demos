package edu.mum.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.domain.Address;
import edu.mum.domain.Member;
import edu.mum.service.AddressService;
import edu.mum.service.MemberService;

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
    address.setMember(member);
    member.getAddresses().add(address);
    addressService.save(address);
   
    Address address2 =  new Address();
    address2.setCity("Red Rock");
    address2.setState("Iowa");
    address2.setMember(member);
    member.getAddresses().add(address2);
    
    // Need to UPDATE address2 - even though NEVER saved...This way it will MERGE Member
    addressService.update(address2);

    memberService.findOne(member.getId());
    
    System.out.println();
    System.out.println("Member Name : " + member.getFirstName() + "  " +  member.getLastName() );
    for (Address addresse : member.getAddresses()) {
        System.out.println("Address : " + addresse.getCity() + 
				"   " + addresse.getState());
    }

 
  }
  
  }