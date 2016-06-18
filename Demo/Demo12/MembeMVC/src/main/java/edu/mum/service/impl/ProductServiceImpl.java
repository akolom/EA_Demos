package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.domain.Product;
import edu.mum.rest.service.impl.ProductRestServiceImpl;
import edu.mum.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
 	@Autowired
	private ProductRestServiceImpl productRestService;

	public List<Product> findAll() {
		return productRestService.findAll();
	}

 
 	public void addProduct(Product product) {
 		productRestService.save(product);
	}
	
	public Product findOne(long productID) {
		return productRestService.findOne(productID);
	}

 

}
