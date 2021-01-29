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
import com.epam.tcfp.zooproject.entity.Order;

public class GetOrdersStatusService implements Service {
	private final OrderDAO orderDAO = new OrderDAO();
	public static final String ALL_ORDERS = "allOrders";
	public static final String EPMTY = "empty";
	public static final String ORDERS_NOT_EXIST = "ordersNotExist";
	public static final String TRUE = "true";
	public static final String FALSE = "false";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {
		HttpSession session = request.getSession(true);
		ArrayList<Order> orders = (ArrayList<Order>) orderDAO.getOrders();
		if(!orders.isEmpty()) {		
		session.setAttribute(ALL_ORDERS, orders);
		session.setAttribute(ORDERS_NOT_EXIST, FALSE);
		}
		else {
			session.setAttribute(ORDERS_NOT_EXIST, TRUE);
		}
		response.sendRedirect("adminManageOrderStatus.jsp");		
	}
}
