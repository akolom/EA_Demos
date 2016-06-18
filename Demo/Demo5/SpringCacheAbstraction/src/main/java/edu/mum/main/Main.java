package edu.mum.main;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.stereotype.Component;

import edu.mum.domain.Address;
import edu.mum.domain.Member;
import edu.mum.service.AddressService;
import edu.mum.service.MemberService;

/*
 * This is the NamedEntitygraph - similar to Join fetch
 * SINGLE Select
 * ...BUT OUTER JOIN...
 * So we get PEAT Moss WITHOUT Addresses

 */
@Component
public class Main {
	
	  
	  @Autowired
	  AddressService addressService;

	  @Autowired
	  MemberService memberService;

	  @PersistenceContext
	  EntityManager entityManager;
	  
 	  

  public static void main(String[] args) {

	  
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");
    applicationContext.getBean(Main.class).mainInternal(applicationContext);
  }
    private void mainInternal(ApplicationContext applicationContext) {
 
  
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
    
 	    	Statistics stats = entityManager.getEntityManagerFactory().unwrap(SessionFactory.class).getStatistics();
		   stats.clear();
 		   
//		   memberService.findByGraph();
		   
		   // Query actually Caches Member so NO Put during findOne below   -------
/*		  entityManager.createNamedQuery("Member.findById")
				    .setParameter("memberNumber", 1)
 				    .getSingleResult();

		  System.out.println("      ************* Initial Query **********");

		  printStatistics();
*/
			  long start = System.currentTimeMillis();
//			   member = memberService.findOne(1L);   //this is a miss & a PUT [ if no cached query]
			  List<Member> m = (List<Member>)memberService.findAllJoinFetch();   //this is a miss & a PUT [ if no cached query]
			  long elapsed = System.currentTimeMillis() - start;
			  System.out.println("FIRST FindOne Time: " + elapsed + " ms ");	  
			   
			  start = System.currentTimeMillis();
//			   member = memberService.findOne(1L);   //this is a miss & a PUT [ if no cached query]
			 m = (List<Member>)memberService.findAllJoinFetch();   //this is a miss & a PUT [ if no cached query]
			 elapsed = System.currentTimeMillis() - start;
			  System.out.println("SECOND FindOne Time: " + elapsed + " ms ");	  

			   
			  start = System.currentTimeMillis();
//			   member = memberService.findOne(1L);   //this is a miss & a PUT [ if no cached query]
			m = (List<Member>)memberService.findAllJoinFetch();   //this is a miss & a PUT [ if no cached query]
			 elapsed = System.currentTimeMillis() - start;
			  System.out.println("THIRD FindOne Time: " + elapsed + " ms ");	  
			  
			  start = System.currentTimeMillis();
//			   member = memberService.findOne(1L);   //this is a miss & a PUT [ if no cached query]
			m = (List<Member>)memberService.findAllJoinFetch();   //this is a miss & a PUT [ if no cached query]
			 elapsed = System.currentTimeMillis() - start;
			  System.out.println("FOURTH FindOne Time: " + elapsed + " ms ");	  
			  
 		   
/*		   member = memberService.findOne(2L);   //this is a miss & a PUT
		   member = memberService.findOne(2L);   
		   member = memberService.findOne(2L);
		   member = memberService.findOne(2L);
*/		
		   
     }
    
    
   
    
    }
  
   



