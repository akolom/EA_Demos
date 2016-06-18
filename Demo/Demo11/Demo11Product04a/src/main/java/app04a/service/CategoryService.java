package app04a.service;

import java.util.List;

import app04a.domain.Category;
import app04a.domain.Product;

 public interface CategoryService  {	
		public Category getCategory(int id);	
		public List<Category> getAll();	
}
 
