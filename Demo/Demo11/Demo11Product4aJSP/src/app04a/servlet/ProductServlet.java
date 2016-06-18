package app04a.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app04a.domain.Category;
import app04a.domain.Product;
import app04a.repository.CategoryRepository;
import app04a.repository.ProductRepository;
import app04a.repositoryimpl.CategoryRepositoryImpl;
import app04a.repositoryimpl.ProductRepositoryFactory;

/**
 * Servlet implementation class ProductServlet
 */
 
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProductRepository productRepository = ProductRepositoryFactory.getProductRepository();
	
	CategoryRepository categoryRepository =  CategoryRepositoryImpl.INSTANCE;
	
    /**
     * Default constructor. 
     */
    public ProductServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       List<Category> categories = categoryRepository.getAll();
	        request.setAttribute("categories", categories);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/ProductForm.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		String name = request.getParameter("name").trim();
		String description = request.getParameter("description").trim();
		String price = request.getParameter("price").trim();
		String categoryId = request.getParameter("category.id").trim();
		 		
	      // create model
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        try {
            product.setPrice(Float.parseFloat(price ));
        } catch (NumberFormatException e) {
        }

        try {
            int id = Integer.parseInt(categoryId);
            Category category = categoryRepository.getCategory(id);
            product.setCategory(category);
            
        } catch (NumberFormatException e) {
        }

        productRepository.save(product);
 
		request.setAttribute("product", product);
 			
			
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/ProductDetails.jsp");
		requestDispatcher.forward(request, response);
		
		}
 

}
