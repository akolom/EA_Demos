package app04a.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app04a.domain.Product;
import app04a.repository.ProductRepository;
import app04a.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
    	public List<Product> getAll() {
		return productRepository.getAll();
	}
	
	public void save(Product product) {
		productRepository.save(product);
		return ;
	}
	
	
		   
}
 
