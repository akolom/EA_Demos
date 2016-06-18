package edu.mum.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import edu.mum.domain.Product;
import edu.mum.domain.ProductionStatus;
import edu.mum.service.ProductService;

public class ProductItemWriter implements ItemWriter<Product> {

 	private ProductService productService;

	@Override
	public void write(List<? extends Product> products) throws Exception {
		for (Product product : products) {
			productService.save(product);
		}
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}

