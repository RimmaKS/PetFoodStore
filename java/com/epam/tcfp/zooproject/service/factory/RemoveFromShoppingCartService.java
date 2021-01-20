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

public class RemoveFromShoppingCartService implements Service {
	private ProductDAO productDAO = new ProductDAO();
	public static final String CART_EDIT_STATUS = "cartEditStatus";
	public static final String PRODUCT_TO_REMOVE = "productToRemove";
	public static final String SHOPPING_CART = "shoppingCart";
	public static final String NOTHING_TO_DELETE = "nothingToDelete";
	public static final String ITEM_DELETED = "itemDeleted";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {
		String removeStatus = CART_EDIT_STATUS;
		HttpSession session = request.getSession(true);

		String productId = request.getParameter(PRODUCT_TO_REMOVE);
		Product product = productDAO.selectProduct(Long.parseLong(productId));
		
		ShoppingCart cart = (ShoppingCart) session.getAttribute(SHOPPING_CART);
		if (cart == null) {
			session.setAttribute(removeStatus, NOTHING_TO_DELETE);
		}
		else {
		if (cart.getItems().contains(product)) {
			int indexOfProduct = cart.getItems().indexOf(product);
			Product existingProduct = cart.getItems().get(indexOfProduct);
			cart.getItems().remove(existingProduct);
			session.setAttribute(removeStatus, ITEM_DELETED);
		} else {
			session.setAttribute(removeStatus, NOTHING_TO_DELETE);
		}
		cart.calculateTotalCost();
	}
		response.sendRedirect(request.getHeader("Referer"));
	}
}
