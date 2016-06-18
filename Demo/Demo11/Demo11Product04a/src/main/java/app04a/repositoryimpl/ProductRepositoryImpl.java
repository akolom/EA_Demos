package app04a.repositoryimpl;

import java.util.ArrayList;
import java.util.List;

 import app04a.domain.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app04a.domain.Product;
import app04a.repository.CategoryRepository;
import app04a.repository.ProductRepository;

@Repository
 public class ProductRepositoryImpl implements ProductRepository {
	
	CategoryRepository categoryRepository;
	
		private List<Product> listOfProducts ;

		/*
		 * NOTICE DI on Constructor
		 */
		@Autowired
	    public ProductRepositoryImpl(CategoryRepository categoryRepository) {
	    	this.categoryRepository =  categoryRepository;
	    	listOfProducts = new ArrayList<Product>();
	    	Category category= categoryRepository.getCategory(1);
	        Product bowTie = new Product(1, "Bow Tie", "Loud", (float) 22.0,  category);
 	        listOfProducts.add(bowTie);
 	    }

  	
		@Override
		public List<Product> getAll() {
		return listOfProducts;
	}
	
	@Override
	public void save(Product product) {
		listOfProducts.add(product);
		return ;
	}
	
 
		   
}
 
