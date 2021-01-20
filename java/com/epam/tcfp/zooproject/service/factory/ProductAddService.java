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


public class ProductAddService implements Service {
	private ProductDAO productDAO = new ProductDAO();
	public static final String PASS_TO_ADD = "passtoaddnew";
	public static final String ACTION = "action";
	public static final String ADD_PRODUCT = "addproduct";
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
	public static final String PRODUCT_ADD_STATUS = "productaddstatus";
	public static final String UPDATE_STATUS = "added";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {		
		HttpSession session = request.getSession(true);
		String toPage = "";
		String action = (String) session.getAttribute(ACTION);
		
		if(action.equalsIgnoreCase(ADD_PRODUCT)) {
			session.setAttribute(ACTION, PASS_TO_ADD);
			toPage = "adminAddProduct.jsp";
		}
		
		if(action.equalsIgnoreCase(PASS_TO_ADD)) {		
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
        
        Product product = new Product();
        product.setTitle(title);
        product.setBrand(brand);
        product.setForm(form);
        product.setBreed(breed);
        product.setAgeRate(age);
        product.setWeight(weight);
        product.setAnimalType(animalType);
        product.setSummary(summary);
        product.setPrice(BigDecimal.valueOf(Float.parseFloat(price)));
        product.setQuantity(Long.parseLong(quantity));
        
        productDAO.insertProduct(product);        
		ArrayList<Product> products = (ArrayList<Product>)productDAO.getProducts();		
		session.setAttribute(PRODUCTS, products);      
		session.setAttribute(PRODUCT_ADD_STATUS, UPDATE_STATUS);
        toPage = "adminManageProducts.jsp";
		}
		response.sendRedirect(toPage);
	}
}
