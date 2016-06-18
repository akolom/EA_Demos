package edu.mum.dao.impl;

 

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.dao.MemberDao;
import edu.mum.domain.Member;


@SuppressWarnings("unchecked")
@Repository
public class MemberDaoImpl extends GenericDaoImpl<Member> implements MemberDao {

	public MemberDaoImpl() {
		super.setDaoType(Member.class );
		}

	public Member findByMemberNumber(Integer number) {
	     
		Query query = entityManager.createQuery("select m from MEMBER m  where m.memberNumber =:number");
		return (Member) query.setParameter("number", number).getSingleResult();
			     

	}
	
	public List<Member> findByMemberNumberRange(Integer memberId, Integer memberId2)
	{
		Query query = entityManager.createQuery("select m from MEMBER m  "
				+ "where m.memberNumber >= :memberId and m.memberNumber <= :memberId2");
		return (List<Member>) query.setParameter("memberId", memberId).
				setParameter("memberId2", memberId2).getResultList();
			     

	}



 }