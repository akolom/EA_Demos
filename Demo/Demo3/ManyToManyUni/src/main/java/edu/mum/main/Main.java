package edu.mum.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import edu.mum.domain.Category;
import edu.mum.domain.Product;
import edu.mum.domain.ProductionStatus;
import edu.mum.service.CategoryService;
import edu.mum.service.ProductService;

public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");

     CategoryService categoryService = (CategoryService) ctx.getBean("categoryServiceImpl");


  
    Category category = new Category();
    category.setName("Sports");
    
    Category category2 = new Category();
    category2.setName("Toys");
    
 
    Product product = new Product();
    product.setName("Sled");
    product.setDescription("Winter time fun");
    product.setPrice(22.0F);

 // Second product
    
    Product product2 = new Product();
    product2.setName("Skates");
    product2.setDescription("Winter time gliding");
    product2.setPrice(44.0F);
    product2.setStatus(ProductionStatus.PRODUCTION);

    
    category.addProduct(product2);
    category.addProduct(product);
    categoryService.save(category);

    category2.addProduct(product2);
    category2.addProduct(product);
    // Need to merge detached objects - will also persist parent
    categoryService.update(category2);

	   category =  categoryService.findByCategoryName(category.getName());
	    
	    System.out.println("--------Category #1-> Products--------------");

	    System.out.println("Category Name : " + category.getName());

		for (Product producte : category.getProducts()) {
		System.out.println("Product Name : " + producte.getName());
		}


		   category2 =  categoryService.findByCategoryName(category2.getName());
		    
		    System.out.println("--------Category #2-> Products--------------");

		    System.out.println("Category Name : " + category2.getName());

			for (Product producte : category2.getProducts()) {
			System.out.println("Product Name : " + producte.getName());
			}


			//-------------- ADD ANOTHER product 
			
			
		    product = new Product();
		    product.setName("Skis");
		    product.setDescription("Two for one");
		    product.setPrice(144.0F);

		    
		    category.addProduct(product);
		    categoryService.update(category);

		    System.out.println("--------Category -> ADDITIONAL Products--------------");

		    System.out.println("Category Name : " + category.getName());

			for (Product producte : category.getProducts()) {
			System.out.println("Product Name : " + producte.getName());
			}


			
  }  
  }