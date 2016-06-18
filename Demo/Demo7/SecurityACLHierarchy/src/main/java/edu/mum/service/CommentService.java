package edu.mum.service;

import java.util.List;

import edu.mum.domain.Comment;
 
public interface CommentService {

	public void save(Comment comment);
  	public void update(Comment comment);

	public void delete(Comment comment);
	public List<Comment> findAll();
 }
