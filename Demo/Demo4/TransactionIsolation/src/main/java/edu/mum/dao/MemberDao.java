package edu.mum.dao;

import java.util.List;

import edu.mum.domain.Member;

public interface MemberDao extends GenericDao<Member> {
      
	public Member findByMemberNumber(Integer number);
	public void flush();
	public void refresh(Member member);
	public List<Member> findByMemberNumberRange(Integer memberId, Integer memberId2);
}
