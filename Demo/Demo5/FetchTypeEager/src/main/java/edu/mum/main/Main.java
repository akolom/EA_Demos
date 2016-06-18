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
 * N+1 issue when Many side is declare EAGER
 * One Fetch of LIST of Members
 * N Fetches of each Members List of Addresses
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

    // No Addresses - will show ONLY in  Left Outer Join
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

    Order order = new Order();
    order.setOrderNumber("52");

    Order order2 = new Order();
    order2.setOrderNumber("42");

    member.addOrder(order);
    member.addOrder(order2);

    
    memberService.save(member);
    memberService.save(member2);
    memberService.save(member3);
    
    address =  new Address();
    address.setCity("Russia");
    address.setState("Iowa");
    addressService.save(address);
    
  //  fetch=FetchType.EAGER -- N+1
   List<Member> members = memberService.findAll();
  
   System.out.println("N+1 ISSUE");

   for (Member membere : members) {
	   
	   System.out.println("Member Name : " + membere.getFirstName() + "  " +  membere.getLastName() );
	
	   for (Address addresse : membere.getAddresses()) {
	       System.out.println("Address : " + addresse.getCity() + 
					"   " + addresse.getState());
	   }
   }

    /*   for (Order ordere : member.getOrders()) {
       System.out.println("Order : " + ordere.getOrderNumber());
   }
*/
 
  } 
  }