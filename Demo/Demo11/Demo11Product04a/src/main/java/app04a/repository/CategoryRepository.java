package app04a.repository;

import java.util.List;

import app04a.domain.Category;
import app04a.domain.Product;

 public interface CategoryRepository   {
	
 
		public Category getCategory(int id);
		
		public List<Category> getAll();
		   
	
}
 
