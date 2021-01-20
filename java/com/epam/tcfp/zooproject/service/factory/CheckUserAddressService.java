package com.epam.tcfp.zooproject.service.factory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.tcfp.zooproject.dao.UserAddressDAO;
import com.epam.tcfp.zooproject.dao.UserDAO;
import com.epam.tcfp.zooproject.entity.User;
import com.epam.tcfp.zooproject.entity.UserAddress;

public class CheckUserAddressService implements Service {
    private UserDAO userDAO = new UserDAO();
	private UserAddressDAO userAddressDAO = new UserAddressDAO();
	public static final String USER = "user";
	public static final String ADDRESS = "address";
	public static final String ADDRESS_STATUS = "addressStatus";
	public static final String ADDRESS_EXISTS = "exists";
	public static final String ADDRESS_NOT_EXISTS = "notExists";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {
		String toPage = "orderCheckout.jsp";		
		HttpSession session = request.getSession(true);
		String userEmail = (String) session.getAttribute(USER);
			
		User user = userDAO.selectUser(userEmail);
		long userId = user.getId();	
		boolean userAddressExists = userAddressDAO.checkIfUserAddressExists(userId);

		if(userAddressExists) {
			UserAddress userAddress = userAddressDAO.getUserAddress(userId);
			session.setAttribute(ADDRESS, userAddress);
			session.setAttribute(ADDRESS_STATUS, ADDRESS_EXISTS);
		}
		else {
			session.setAttribute(ADDRESS, null);
			session.setAttribute(ADDRESS_STATUS, ADDRESS_NOT_EXISTS);
		}
		response.sendRedirect(toPage);
	}
}
