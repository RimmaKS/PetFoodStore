package com.epam.tcfp.zooproject.service.factory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.tcfp.zooproject.dao.UserDAO;
import com.epam.tcfp.zooproject.entity.User;

public class GetUserAccountDataService implements Service {
	private final UserDAO userDAO = new UserDAO();
	public static final String USER_DATA = "userData";
	public static final String USER = "user";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {
		HttpSession session = request.getSession(true);
		String userEmail = (String) session.getAttribute(USER);

		User user = userDAO.selectUser(userEmail);
		session.setAttribute(USER_DATA, user);
		response.sendRedirect("userData.jsp");
	}
}
