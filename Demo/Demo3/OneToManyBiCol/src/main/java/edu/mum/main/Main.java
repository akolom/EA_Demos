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
   
    //**
    addressService.save(address);
    

    // No Address associated with Member when saving JUST member
    // Address is NOT updated with FK
    // good practice - SAVE BOTH SIDES -
   address.setMember(member);
   member.getAddresses().add(address);
   memberService.save(member);

    // Using CascadeType.MERGE & a convenience method will help...save both sides
/*    member.addAddress(address);   
    member = memberService.update(member);
*/
   
   member = memberService.findOne(member.getId());
   
   System.out.println();
   System.out.println("***********       Save Member ONLY - doesn't save address **************");

   System.out.println("Member Name : " + member.getFirstName() + "  " +  member.getLastName() );

   // No Addresses will Show up
   for (Address addresse : member.getAddresses()) {
       System.out.println("Address : " + addresse.getCity() + 
				"   " + addresse.getState());
   }


 //--------------------------------------------------------------------
  
    address =  new Address();
    address.setCity("Red Rock");
    address.setState("Iowa");
    address.setMember(member);
     
    // will SAVE BOTH SIDES WHEN saving side with Foreign key
    // good practice - SAVE BOTH SIDES -
    addressService.save(address);
   
    member = memberService.findOne(member.getId());

    System.out.println();
    System.out.println("***********       Save Address ONLY - saves BOTH **************");
    System.out.println("Member Name : " + member.getFirstName() + "  " +  member.getLastName() );

    // Since saved on Foreign Key side - both sides will be saved...
    for (Address addresse : member.getAddresses()) {
        System.out.println("Address : " + addresse.getCity() + 
 				"   " + addresse.getState());
    }


  }
  
  }