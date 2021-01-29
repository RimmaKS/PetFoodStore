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

public class UpdateOrderStatusService implements Service {
	private final OrderDAO orderDAO = new OrderDAO();
	public static final String ORDER_TO_CHANGE = "orderToChange";
	public static final String ORDER_STATUS = "orderStatus";
	public static final String ALL_ORDERS = "allOrders";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {
		HttpSession session = request.getSession(true);
		
		String orderId = request.getParameter(ORDER_TO_CHANGE); 
		String statusId = request.getParameter(ORDER_STATUS); 		
		
		orderDAO.updateStatus(orderId, statusId);
		ArrayList<Order> orders = (ArrayList<Order>) orderDAO.getOrders();
		session.setAttribute(ALL_ORDERS, orders);
		response.sendRedirect("adminManageOrderStatus.jsp");
	}
}
