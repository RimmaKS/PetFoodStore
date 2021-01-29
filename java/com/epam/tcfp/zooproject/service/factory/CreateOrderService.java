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
	private final OrderDAO orderDAO = new OrderDAO();
	private final UserDAO userDAO = new UserDAO();
	private final OrderItemDAO orderItemDAO = new OrderItemDAO();
	private final UserAddressDAO userAddressDAO = new UserAddressDAO();
	private final ProductDAO productDAO = new ProductDAO();
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
	private Order order;
	
	private String userEmail;
	private String countryName;
	private String zipCode;
	private String regionName;
	private String cityName;
	private String streetName;
	private String buildingName;
	private String apartmentName;
	private String addressNoChange;
	private String addressChange;
	private String newAddress;
	private long userId;
	private String orderId;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {

		HttpSession session = request.getSession(true);
		ShoppingCart cart = (ShoppingCart) session.getAttribute(SHOPPING_CART);

		setAddressValues(request, session);
		User user = userDAO.selectUser(userEmail);
		this.userId = user.getId();

		createOrder();
		setUserId(this.userId);
		setTotalCost(cart);
		setUserAddress(userAddressDAO.getAddressId(this.userId));
		this.orderId = orderDAO.createOrder(this.order);
		insertOrderItems(cart);

		session.setAttribute(CONFIRMATION_NUMBER, orderId);
		session.setAttribute(SHOPPING_CART, null);
		response.sendRedirect("orderConfirmation.jsp");
	}
	
	public void createOrder() {
		this.order = new Order();
		this.order.setStatusId(1);
	}
	
	public void setUserId(long userId) {
		this.order.setUserId(userId);
	}
	
	public void setTotalCost(ShoppingCart cart) {
		this.order.setTotalCost(cart.getTotalCost());
	}
	
	public void setUserAddress(String existingAddressId) {
		UserAddress address = null;
		if (this.addressNoChange != null && this.addressNoChange.equals(TRUE)) {
			this.order.setDeliveryAddressId(Long.parseLong(existingAddressId));
		} else if (this.addressChange != null && this.addressChange.equals(TRUE)) {
			address = new UserAddress();
			address.setUserId(this.userId);
			address.setZipCode(this.zipCode);
			address.setCountry(this.countryName);
			address.setRegion(this.regionName);
			address.setCity(this.cityName);
			address.setStreet(this.streetName);
			address.setBuilding(this.buildingName);
			address.setApartment(this.apartmentName);
			userAddressDAO.updateAddress(address);
			this.order.setDeliveryAddressId(Long.parseLong(existingAddressId));
		} else if (this.newAddress != null && this.newAddress.equals(TRUE)) {
			address = new UserAddress();
			address.setUserId(this.userId);
			address.setZipCode(this.zipCode);
			address.setCountry(this.countryName);
			address.setRegion(this.regionName);
			address.setCity(this.cityName);
			address.setStreet(this.streetName);
			address.setBuilding(this.buildingName);
			address.setApartment(this.apartmentName);
			String newAddressId = userAddressDAO.insertAddress(address);
			order.setDeliveryAddressId(Long.parseLong(newAddressId));
		}
	}
	
	public void insertOrderItems(ShoppingCart cart) {
		ArrayList<Product> cartItems = (ArrayList<Product>) cart.getItems();
		OrderItem orderItem;
		for (Product item : cartItems) {
			orderItem = new OrderItem();
			orderItem.setOrderId(Long.parseLong(this.orderId));
			orderItem.setProductId(item.getId());
			orderItem.setPrice(item.getPrice());
			orderItem.setQuantity(item.getQuantity());
			orderItemDAO.insertOrderItem(orderItem);
			productDAO.subtractAmount(item.getId(), item.getQuantity());
		}
	}	
	
	public void setAddressValues(HttpServletRequest request, HttpSession session) {
		this.userEmail = (String) session.getAttribute(USER);
		this.countryName = request.getParameter(COUNTRY);
		this.zipCode = request.getParameter(ZIP_CODE);
		this.regionName = request.getParameter(REGION);
		this.cityName = request.getParameter(CITY);
		this.streetName = request.getParameter(STREET);
		this.buildingName = request.getParameter(BUILDING);
		this.apartmentName = request.getParameter(APARTMENT);
		this.addressNoChange = request.getParameter(ADDRESS_NO_CHANGE);
		this.addressChange = request.getParameter(ADDRESS_CHANGE);
		this.newAddress = request.getParameter(ADDRESS_NEW);
	}
}
