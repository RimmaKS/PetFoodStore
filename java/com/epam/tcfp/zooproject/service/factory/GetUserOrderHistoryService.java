package com.epam.tcfp.zooproject.service.factory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.tcfp.zooproject.dao.OrderInfoDAO;
import com.epam.tcfp.zooproject.dao.UserDAO;
import com.epam.tcfp.zooproject.entity.OrderInfo;
import com.epam.tcfp.zooproject.entity.User;

public class GetUserOrderHistoryService implements Service {
	private final OrderInfoDAO orderInfoDAO = new OrderInfoDAO();
    private final UserDAO userDAO = new UserDAO();
    public static final String USER = "user";
    public static final String USER_ORDERS = "userOrders";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {
		HttpSession session = request.getSession(true);
		String userEmail = (String) session.getAttribute(USER);	
		User user = userDAO.selectUser(userEmail);
		long userId = user.getId();
		ArrayList<OrderInfo> orders = (ArrayList<OrderInfo>)orderInfoDAO.getUserOrders(userId);
		
		session.setAttribute(USER_ORDERS, orders);
		response.sendRedirect("userOrderHistory.jsp");
	}
}
