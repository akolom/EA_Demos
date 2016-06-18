package app04a.service;

import java.util.List;

import app04a.domain.Product;

 public interface ProductService   {
	
	public List<Product> getAll();
	
	public void save(Product product);
	
	
		   
}
 
