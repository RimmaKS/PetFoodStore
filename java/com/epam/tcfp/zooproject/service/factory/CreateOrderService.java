package com.epam.tcfp.zooproject.service.factory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.tcfp.zooproject.dao.OrderDAO;
import com.epam.tcfp.zooproject.dao.OrderItemDAO;
import com.epam.tcfp.zooproject.dao.ProductDAO;
import com.epam.tcfp.zooproject.dao.UserAddressDAO;
import com.epam.tcfp.zooproject.dao.UserDAO;
import com.epam.tcfp.zooproject.entity.Order;
import com.epam.tcfp.zooproject.entity.OrderItem;
import com.epam.tcfp.zooproject.entity.Product;
import com.epam.tcfp.zooproject.entity.ShoppingCart;
import com.epam.tcfp.zooproject.entity.User;
import com.epam.tcfp.zooproject.entity.UserAddress;

public class CreateOrderService implements Service {
	private OrderDAO orderDAO = new OrderDAO();
	private UserDAO userDAO = new UserDAO();
	private OrderItemDAO orderItemDAO = new OrderItemDAO();
	private UserAddressDAO userAddressDAO = new UserAddressDAO();
	private ProductDAO productDAO = new ProductDAO();
	public static final String USER = "user";
	public static final String COUNTRY = "country";
	public static final String ZIP_CODE = "zipCode";
	public static final String REGION = "region";
	public static final String CITY = "city";
	public static final String STREET = "street";
	public static final String BUILDING = "building";
	public static final String APARTMENT = "apartment";
	public static final String SHOPPING_CART = "shoppingCart";
	public static final String ADDRESS_NO_CHANGE = "addressNoChange";
	public static final String ADDRESS_CHANGE = "addressChange";
	public static final String ADDRESS_NEW = "newAddress";
	public static final String TRUE = "true";
	public static final String CONFIRMATION_NUMBER = "confirmationNumber";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {

		HttpSession session = request.getSession(true);

		String userEmail = (String) session.getAttribute(USER);
		String country = request.getParameter(COUNTRY);
		String zipCode = request.getParameter(ZIP_CODE);
		String region = request.getParameter(REGION);
		String city = request.getParameter(CITY);
		String street = request.getParameter(STREET);
		String building = request.getParameter(BUILDING);
		String apartment = request.getParameter(APARTMENT);
		ShoppingCart cart = (ShoppingCart) session.getAttribute(SHOPPING_CART);

		User user = userDAO.selectUser(userEmail);
		long userId = user.getId();
		String orderId = "";
		try {
			Order order = new Order();
			order.setUserId(userId);
			order.setStatusId(1);
			order.setTotalCost(cart.getTotalCost());

			String addressNoChange = request.getParameter(ADDRESS_NO_CHANGE);
			String addressChange = request.getParameter(ADDRESS_CHANGE);
			String newAddress = request.getParameter(ADDRESS_NEW);
			UserAddress address = null;
			String existingAddressId = userAddressDAO.getAddressId(userId);
			if (addressNoChange != null && addressNoChange.equals(TRUE)) {
				order.setDeliveryAddressId(Long.parseLong(existingAddressId));
			} else if (addressChange != null && addressChange.equals(TRUE)) {
				address = new UserAddress();
				address.setUserId(userId);
				address.setZipCode(zipCode);
				address.setCountry(country);
				address.setRegion(region);
				address.setCity(city);
				address.setStreet(street);
				address.setBuilding(building);
				address.setApartment(apartment);
				userAddressDAO.updateAddress(address);
				order.setDeliveryAddressId(Long.parseLong(existingAddressId));
			} else if (newAddress != null && newAddress.equals(TRUE)) {
				address = new UserAddress();
				address.setUserId(userId);
				address.setZipCode(zipCode);
				address.setCountry(country);
				address.setRegion(region);
				address.setCity(city);
				address.setStreet(street);
				address.setBuilding(building);
				address.setApartment(apartment);
				String newAddressId = userAddressDAO.insertAddress(address);
				order.setDeliveryAddressId(Long.parseLong(newAddressId));
			}

			orderId = (orderDAO.createOrder(order));

			ArrayList<Product> cartItems = (ArrayList<Product>) cart.getItems();
			OrderItem orderItem;
			for (Product item : cartItems) {
				orderItem = new OrderItem();
				orderItem.setOrderId(Long.parseLong(orderId));
				orderItem.setProductId(item.getId());
				orderItem.setPrice(item.getPrice());
				orderItem.setQuantity(item.getQuantity());
				orderItemDAO.insertOrderItem(orderItem);
				productDAO.subtractAmount(item.getId(), item.getQuantity());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		session.setAttribute(CONFIRMATION_NUMBER, orderId);
		session.setAttribute(SHOPPING_CART, null);
		response.sendRedirect("orderConfirmation.jsp");
	}
}
