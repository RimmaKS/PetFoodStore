package com.epam.tcfp.zooproject.service.factory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.tcfp.zooproject.dao.ProductDAO;
import com.epam.tcfp.zooproject.entity.Product;

public class ProductShowService implements Service {
	private ProductDAO productDAO = new ProductDAO();
	public static final String PRODUCTS = "products";
	public static final String EDIT_PAGE = "editpage";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {
		
		String toPage = "productShowAllCard.jsp";
		HttpSession session = request.getSession(true);
		ArrayList<Product> products = (ArrayList<Product>)productDAO.getProducts();		
		
		session.setAttribute(PRODUCTS, products);
		
		
		String toEdit = request.getParameter(EDIT_PAGE);		
		if(toEdit != null && toEdit.equalsIgnoreCase(EDIT_PAGE)) {
			toPage = "adminManageProducts.jsp";
		}		
		response.sendRedirect(toPage);
}
}
