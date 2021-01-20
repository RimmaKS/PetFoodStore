package com.epam.tcfp.zooproject.service.factory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.tcfp.zooproject.dao.ProductDAO;
import com.epam.tcfp.zooproject.entity.Product;
import com.epam.tcfp.zooproject.entity.ShoppingCart;

public class AddToShoppingCartService implements Service {
	private ProductDAO productDAO = new ProductDAO();
	public static final String SHOPPING_CART = "shoppingCart";
	public static final String ADMIN_USER = "isAdmin";
	public static final String USER = "user";
	public static final String GUEST = "guest";
	public static final String TRUE = "true";
	public static final String PRODUCT_TO_ADD = "productToAdd";
	public static final String CART_NOT_ALLOWED = "cartNotAllowed";	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {
		HttpSession session = request.getSession(true);

		String user = (String) session.getAttribute(USER);
		String isAdmin = (String) session.getAttribute(ADMIN_USER);
		String toPage = "";
		if (user.equalsIgnoreCase(GUEST) || isAdmin.equalsIgnoreCase(TRUE)) {
			toPage = "userLogin.jsp";
			session.setAttribute(CART_NOT_ALLOWED, TRUE);
		} else {
			toPage = request.getHeader("Referer");
		}

		String productId = request.getParameter(PRODUCT_TO_ADD);
		Product product = productDAO.selectProduct(Long.parseLong(productId));
		product.setQuantity(1);

		ShoppingCart cart = (ShoppingCart) session.getAttribute(SHOPPING_CART);
		if (cart == null) {
			cart = new ShoppingCart();
			session.setAttribute(SHOPPING_CART, cart);
		}

		if (cart.getItems().contains(product)) {
			int indexOfProduct = cart.getItems().indexOf(product);
			Product existingProduct = cart.getItems().get(indexOfProduct);
			existingProduct.setQuantity(existingProduct.getQuantity() + 1);
		} else {
			product.setQuantity(1);
			cart.addItem(product);
		}
		cart.calculateTotalCost();
		response.sendRedirect(toPage);
	}
}
