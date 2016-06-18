package app04a.repository;

import java.util.List;

import app04a.domain.Category;
import app04a.domain.Product;

 public interface ProductRepository   {
	
 
	
	public List<Product> getAll();
	
	public void save(Product product);
	
}
 
