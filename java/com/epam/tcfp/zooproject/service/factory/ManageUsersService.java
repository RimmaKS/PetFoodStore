package com.epam.tcfp.zooproject.service.factory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.tcfp.zooproject.dao.UserDAO;
import com.epam.tcfp.zooproject.entity.User;

public class ManageUsersService implements Service {
	private UserDAO userDAO = new UserDAO();
	public static final String USERS_LIST = "usersList";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {
		HttpSession session = request.getSession(true);
		ArrayList<User> users = (ArrayList<User>) userDAO.selectAllUsers();
		session.setAttribute(USERS_LIST, users);
		response.sendRedirect("adminManageUsers.jsp");
	}
}
