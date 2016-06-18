package app04a.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app04a.domain.Category;
import app04a.domain.Product;
import app04a.repository.CategoryRepository;
import app04a.repository.ProductRepository;
import app04a.service.CategoryService;
import app04a.service.ProductService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
  	public List<Category> getAll() {
		return categoryRepository.getAll();
	}

	@Override
	public Category getCategory(int id) {
		return categoryRepository.getCategory(id);
 	}
	
	
		   
}
 
