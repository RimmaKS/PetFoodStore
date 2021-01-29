package com.epam.tcfp.zooproject.service.factory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.tcfp.zooproject.dao.UserDAO;
import com.epam.tcfp.zooproject.entity.User;

public class DeactivateUserService implements Service {
	private final UserDAO userDAO = new UserDAO();
	public static final String USER_TO_DEACTIVATE = "userToDeactivate";
	public static final String DEACTIVATION_RESULT = "deactivationResult";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {
		HttpSession session = request.getSession(true);
		
		String name = request.getParameter(USER_TO_DEACTIVATE);
		User user = userDAO.selectUser(name);
		boolean deactivated = userDAO.deactivateUser(user);
		
		session.setAttribute(DEACTIVATION_RESULT, String.valueOf(deactivated));
		

		RequestDispatcher rd = request.getRequestDispatcher("/MANAGEUSERS");
		rd.forward(request,response);
	}
}
