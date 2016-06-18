package edu.mum.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import edu.mum.domain.Category;
import edu.mum.domain.Product;
import edu.mum.service.CategoryService;
import edu.mum.service.ProductService;

public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");

    ProductService productService = (ProductService) ctx.getBean("productServiceImpl");
    CategoryService categoryService = (CategoryService) ctx.getBean("categoryServiceImpl");


  
    Category category = new Category();
    category.setName("Sports");
    
    Category category2 = new Category();
    category2.setName("Toys");
    
 
    Product product = new Product();
    product.setName("Sled");
    product.setDescription("Winter time fun");
    product.setPrice(22.0F);

    product.addCategory(category2);
    product.addCategory(category);
    
    productService.save(product);
    
// Second product
    
    Product product2 = new Product();
    product2.setName("Skates");
    product2.setDescription("Winter time gliding");
    product2.setPrice(44.0F);

    product2.addCategory(category2);
    product2.addCategory(category);
  
    Category category3 = new Category();
    category3.setName("Holiday Gifts");
    
    product2.addCategory(category3);

    
    // Need to merge .. to handle detached categories....
    productService.update(product2);
    

   
   product =  productService.findByProductName(product.getName());
    
   System.out.println();
   System.out.println("---------Product->Categories--------------");
    System.out.println("Product Name : " + product.getName());

	for (Category categorie : product.getCategories()) {
	System.out.println("Category Name : " + categorie.getName());
	}


 		   product2 =  productService.findByProductName(product2.getName());
		    
		    System.out.println("---------Product->Categories--------------");
		    System.out.println("Product TWO Name : " + product2.getName());

			for (Category categorie : product2.getCategories()) {
			System.out.println("Category Name : " + categorie.getName());
			}


			   category =  categoryService.findByCategoryName(category.getName());
			    
 

			    System.out.println("--------Category-> Products--------------");

			    System.out.println("Category Name : " + category.getName());

				for (Product producte : category.getProducts()) {
				System.out.println("Product Name : " + producte.getName());
				}


  }  
  }