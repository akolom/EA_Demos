package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import edu.mum.dao.ProductDao;
import edu.mum.domain.Item;
import edu.mum.domain.Product;
import edu.mum.service.ItemService;
import edu.mum.validation.aop.ServiceValidation;

public class ItemServiceImpl implements ItemService{
	@Autowired
	private Product productDao;

	
	/*
	@Autowired
	private Item itemDao;

  	
 //   @PreAuthorize("hasRole('ROLE_ADMIN')")
  	@ServiceValidation
    public void save( Item item) {  
 // 		 if (product.getStatus() != ProductionStatus.INVALID) 
  			 this.performSave(Item);
 	}
  	
    private void performSave( Item item) {  		
    	itemDao.save(Item);
 	}
  	
  	
    @PreAuthorize("hasPermission(#product,'administration')")
 	@ServiceValidation
    public void update( Item item) {  		
  		this.performUpdate(Item);
 	}
  
    
    private void performUpdate( Item item) {  		
  		productDao.update(Item);
 	}
     
  	
	public List<Item> findAll() {
		return (List<Product>)productDao.findAll();
	}

	public Item findByProductName(String name) {
		return productDao.findByProductName(name);
	}
 
	public Item findOne(Long id) {
		return productDao.findOne(id);
	}
	*/

	@Override
	public void save(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Item> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item findByProductName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
