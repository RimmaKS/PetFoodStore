package com.epam.tcfp.zooproject.service.factory;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.tcfp.zooproject.dao.ProductDAO;
import com.epam.tcfp.zooproject.entity.Product;


public class ProductEditService implements Service {
	private final ProductDAO productDAO = new ProductDAO();
	public static final String ACTION = "action";
	public static final String PRODUCT_TITLE = "producttitle";
	public static final String PRODUCT_BRAND = "productbrand";
	public static final String PRODUCT_FORM = "productform";
	public static final String PRODUCT_BREED = "productbreed";
	public static final String PRODUCT_AGE = "productage";
	public static final String PRODUCT_WEIGHT = "productweight";
	public static final String PRODUCT_ANIMAL_TYPE = "productanimaltype";
	public static final String PRODUCT_SUMMARY = "productsummary";
	public static final String PRODUCT_PRICE = "productprice";
	public static final String PRODUCT_QUANTITY = "productquantity";	
	public static final String PRODUCTS = "products";
	public static final String PRODUCT_TO_EDIT = "producttoedit";
	public static final String FILL_FORM = "fillForm";
	public static final String PASS_TO_UPDATE = "passtoupdate";
	public static final String PRODUCT_ID = "productid";
	public static final String PRODUCT_UPDATE_STATUS = "productupdatestatus";
	public static final String UPDATE_STATUS = "updated";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {
		HttpSession session = request.getSession(true);
		String toPage = "";
		
		String productId = request.getParameter(PRODUCT_TO_EDIT); 
		
		if(((String) session.getAttribute(FILL_FORM)).equalsIgnoreCase(FILL_FORM)) {
			Product product = productDAO.selectProduct(Long.parseLong(productId));
			session.setAttribute(PRODUCT_TO_EDIT, product);
			toPage = "adminEditProduct.jsp";
		}
		
		if(((String) session.getAttribute(ACTION)).equalsIgnoreCase(PASS_TO_UPDATE)) {	
		Product product = updateProduct(request);              
        productDAO.updateProduct(product);
        
		ArrayList<Product> products = (ArrayList<Product>)productDAO.getProducts();		
		session.setAttribute(PRODUCTS, products); 
		session.setAttribute(PRODUCT_UPDATE_STATUS, UPDATE_STATUS); 
        toPage = "adminManageProducts.jsp";        
		}

		response.sendRedirect(toPage);		
	}	
	 
	public Product updateProduct(HttpServletRequest request) {
		String id = request.getParameter(PRODUCT_ID);
		String title = request.getParameter(PRODUCT_TITLE);
        String brand = request.getParameter(PRODUCT_BRAND);
        String form = request.getParameter(PRODUCT_FORM); 
        String breed = request.getParameter(PRODUCT_BREED); 
        String age = request.getParameter(PRODUCT_AGE);
        String weight = request.getParameter(PRODUCT_WEIGHT); 
        String animalType = request.getParameter(PRODUCT_ANIMAL_TYPE);
        String summary = request.getParameter(PRODUCT_SUMMARY);
        String price = request.getParameter(PRODUCT_PRICE);
        String quantity = request.getParameter(PRODUCT_QUANTITY);              
        
        Product product = new Product.Builder(title)
        		.id(Long.parseLong(id))
				.brand(brand)
				.form(form)
				.breed(breed)
				.ageRate(age)
				.weight(weight)
				.animalType(animalType)
				.summary(summary)
				.price(BigDecimal.valueOf(Float.parseFloat(price)))
				.quantity(Long.parseLong(quantity))
				.build();
        return product;
	}	
}
