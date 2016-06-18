package edu.mum.dao.impl;

 



import org.springframework.stereotype.Repository;

import edu.mum.dao.CommentDao;
import edu.mum.domain.Comment;


@SuppressWarnings("unchecked")
@Repository
public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {

	public CommentDaoImpl() {
		super.setDaoType(Comment.class );
		}

 
 }