package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.CommentDao;
import edu.mum.domain.Comment;
import edu.mum.domain.Member;
import edu.mum.domain.UserCredentials;
import edu.mum.service.CommentService;

@Service
@Transactional 
public class CommentServiceImpl implements CommentService {
	
 	@Autowired
	private CommentDao commentDao;

    @Autowired
    private MutableAclService mutableAclService;


   	public void save(Comment comment) {

  		commentDao.save(comment);
  		this.initializeAcl(comment);

	}
   	
   	@PreAuthorize("hasPermission(#comment,'administration')")
  	public void update(Comment comment) {

  		commentDao.update(comment);
 
	}
   

   
   	@PreAuthorize("hasRole('ROLE_ADMIN')")
  	public void delete(Comment comment) {

  		commentDao.delete(comment);
 	    ObjectIdentity oid =  new ObjectIdentityImpl(Comment.class, comment.getId());
	    mutableAclService.deleteAcl(oid,false);
	   
	}

   	
	public List<Comment> findAll() {
		return (List<Comment>)commentDao.findAll();
	}

 	private void initializeAcl(Comment comment) {
 	    ObjectIdentity oid =  new ObjectIdentityImpl(Comment.class, comment.getId());
 	    MutableAcl acl = mutableAclService.createAcl(oid);
 	    acl.insertAce(0, BasePermission.ADMINISTRATION,
 	            new PrincipalSid(comment.getMember().getUserCredentials().getUsername()), true);
 	    acl.insertAce(1, BasePermission.DELETE,
 	            new GrantedAuthoritySid("ROLE_ADMIN"), true);
 	    acl.insertAce(2, BasePermission.READ,
 	            new GrantedAuthoritySid("ROLE_USER"), true);
 	    mutableAclService.updateAcl(acl);

 	}

 
}
