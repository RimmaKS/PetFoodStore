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

public class ChangeAmountInShoppingCartService implements Service {
	private ProductDAO productDAO = new ProductDAO();
	public static final String PRODUCT_TO_CHANGE = "productToChange";
	public static final String PRODUCT_QUANTITY = "quantityToChange";
	public static final String SHOPPING_CART = "shoppingCart";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {
		String toPage = "";
		HttpSession session = request.getSession(true);

		int quantity = Integer.parseInt(request.getParameter(PRODUCT_QUANTITY));

		if (quantity < 1 || quantity > 20) {
			toPage = "shoppingCart.jsp";
		} else {
			toPage = request.getHeader("Referer");
		}

		String productId = request.getParameter(PRODUCT_TO_CHANGE);
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
			existingProduct.setQuantity(quantity);
		}

		cart.calculateTotalCost();
		response.sendRedirect(toPage);
	}
}
