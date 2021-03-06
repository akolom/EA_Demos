package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.domain.Member;
import edu.mum.service.MemberService;
import edu.mum.service.TransactionService;

@Service
@Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.READ_COMMITTED)
public class ReadCommittedServiceImpl implements edu.mum.service.ReadCommittedService {

	@Autowired 
	MemberService memberService;

	@Autowired 
	TransactionService transactionService;
	
	public void readCommitted(Member member) {
		Member memberRead = memberService.findOne(member.getId());
		System.out.println( "Read Committed Member Number: " + memberRead.getMemberNumber());
	}

	public void nonRepeatableRead(Member member) {
		Member memberRead = memberService.findOne(member.getId());
		System.out.println( "[Transaction A] Non Repeatable BEFORE COMMIT Member Number: " + memberRead.getMemberNumber());
		
		transactionService.nonRepeatableSave(member);
		
		// IF REPEATABLE - This would be the SAME as the before value IF REPEATABLE
		memberService.refresh(memberRead);
		memberRead = memberService.findOne(member.getId());
		System.out.println( "[Transaction A] Non Repeatable AFTER COMMIT Member Number: " + memberRead.getMemberNumber());
	
	}
	
	public void phantomRead() {
		List<Member> memberRead = memberService.findByMemberNumberRange(102, 105);
		
 		System.out.println( "[Transaction A]Phantom read before COMMIT Member Count: " + memberRead.size());
		transactionService.phantomSave();
		
		// Phantom - This should be the Different from before value 
		memberRead = memberService.findByMemberNumberRange(102, 105);
 		System.out.println( "[Transaction A] Phantom Read AFTER COMMIT Member Count: " + memberRead.size());
	}



}
