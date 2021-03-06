package edu.mum.dao.impl;

 

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityGraph;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.dao.MemberDao;
import edu.mum.domain.Member;
import edu.mum.domain.Order;


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

	public Member findByNamedQuery(Integer number) {

		return (Member) entityManager.createNamedQuery("Member.findByMemberNumber")
	    .setParameter("memberNumber", number)
		    .getSingleResult();
	}

	public Member findByNativeQuery(Integer number) {

		
		Query query = entityManager.createNativeQuery("SELECT m.member_id, m.age, m.title ,m.firstname, m.lastname, m.memberNumber FROM Member m where m.memberNumber = ?", Member.class);
		Member member = (Member) query.setParameter(1,number).getSingleResult();

		return member;
 	}


 }