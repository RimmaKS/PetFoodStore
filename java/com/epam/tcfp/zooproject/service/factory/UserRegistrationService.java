package com.epam.tcfp.zooproject.service.factory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.tcfp.zooproject.dao.UserDAO;
import com.epam.tcfp.zooproject.entity.User;

public class UserRegistrationService implements Service {
	private UserDAO userDAO = new UserDAO();
	public static final String REGISTER_STATUS = "registerStatus";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String EMAIL = "email";
	public static final String MOBILE = "mobile";
	public static final String PASSWORD = "password";
	public static final String PASSWORD_CONFIRMATION = "passwordConfirmation";
	public static final String ADMIN = "admin";
	public static final String PASSWORD_REJECT = "passwordReject";
	public static final String REFERER_PAGE = "Referer";
	public static final String ERROR = "error";
	public static final String DUPLICATE = "duplicate";
	public static final String SUCCESS = "success";
	public static final String ADMIN_USER = "isAdmin";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {
		String toPage = "index.jsp";
		HttpSession session = request.getSession(true);

		String firstName = request.getParameter(FIRST_NAME);
		String lastName = request.getParameter(LAST_NAME);
		String email = request.getParameter(EMAIL);
		String mobile = request.getParameter(MOBILE);
		String password = request.getParameter(PASSWORD);
		String passwordConfirmation = request.getParameter(PASSWORD_CONFIRMATION);
		String isAdmin = request.getParameter(ADMIN);

		String pattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
		boolean passwordMatches = Pattern.matches(pattern, password);
		boolean passwordIsEqual = password.equalsIgnoreCase(passwordConfirmation);
		

		if (!passwordMatches || !passwordIsEqual) {
			session.setAttribute(REGISTER_STATUS, PASSWORD_REJECT);
			response.sendRedirect(request.getHeader(REFERER_PAGE));
		} else {
			boolean registerAsAdmin = false;
			if (ADMIN_USER.equals(isAdmin)) {
				registerAsAdmin = true;
			}

			User newUser = new User(firstName, lastName, email, mobile, password, registerAsAdmin, false);
			int userRegisterStatus = userDAO.insertUser(newUser);
			if (userRegisterStatus == 1) {
				session.setAttribute(REGISTER_STATUS, SUCCESS);
				toPage = "userLogin.jsp";
				request.getRequestDispatcher(toPage).forward(request, response);
			} else {
				if (userRegisterStatus == 2) {
					session.setAttribute(REGISTER_STATUS, DUPLICATE);
				}
				if (userRegisterStatus == 0) {
					session.setAttribute(REGISTER_STATUS, ERROR);
				}
				response.sendRedirect(request.getHeader(REFERER_PAGE));
			}
		}
	}
}
