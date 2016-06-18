package edu.mum.main;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import edu.mum.domain.Member;
import edu.mum.service.MemberService;
import edu.mum.service.ReadCommittedService;
import edu.mum.service.RepeatableReadService;
import edu.mum.service.TransactionService;

@Component
public class Main {
	@Autowired
	MemberService memberService;
	
	@Autowired
	TransactionService transactionService ;
	@Autowired
	ReadCommittedService readCommittedService;
	@Autowired
	RepeatableReadService repeatableReadService;
	
  public static void main(String[] args) {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");
    applicationContext.getBean(Main.class).mainInternal(applicationContext);
  }
    private void mainInternal(ApplicationContext applicationContext) {

     Member member = new Member();
    member.setFirstName("John");
    member.setLastName("Doe");
    member.setMemberNumber(1);
    memberService.save(member);
  
    System.out.println("Member Number: " + member.getMemberNumber());

    System.out.println("-------------Read UnCommitted  Dirty Read -------------------");

    try {
    	transactionService.readUncommitted(member);
    }
    catch (Exception e) {System.out.println("Rollback Exception!!"); }
    
    Member readMember = memberService.findByMemberNumber(1);
    System.out.println("Member Number: " + readMember.getMemberNumber());

    System.out.println();
   System.out.println("-------------Read Committed NO Dirty Read -------------------");

    try {
    	transactionService.readCommitted(member);
    }
    catch (Exception e) {System.out.println("Rollback Exception!!"); }
    
    readMember = memberService.findByMemberNumber(1);
    System.out.println("Member Number: " + readMember.getMemberNumber());
 
    System.out.println();  
    System.out.println("-------------Read Committed NON Repeatable  Read -------------------");

    try {
    	readCommittedService.nonRepeatableRead(member);
    }
    catch (Exception e) {System.out.println("Rollback Exception!!"); }

    System.out.println();
    System.out.println("------------- Repeatable Read There is NO non-repeatable read  -------------------");
    repeatableReadService.repeatableRead(member);

    System.out.println();
    System.out.println("------------- Repeatable Read - Phantom read  -------------------");
 
    member = new Member();
    member.setFirstName("Jim");
    member.setLastName("Dandy");
    member.setMemberNumber(102);
    memberService.save(member);
  
    member = new Member();
    member.setFirstName("Joan");
    member.setLastName("Rivers");
    member.setMemberNumber(105);
    memberService.save(member);
  
    // do it for  read committed
    readCommittedService.phantomRead();

	System.out.println( "FINAL Member Count: " + memberService.findAll().size());
	System.out.println();

	// NOW do it for  repeatable read
    repeatableReadService.phantomRead();

	System.out.println( "FINAL Member Count: " + memberService.findAll().size());

  }
  
  
  }