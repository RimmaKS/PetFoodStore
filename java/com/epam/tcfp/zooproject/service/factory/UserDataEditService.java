package com.epam.tcfp.zooproject.service.factory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epam.tcfp.zooproject.dao.UserDAO;
import com.epam.tcfp.zooproject.entity.User;

public class UserDataEditService implements Service {
    private final UserDAO userDAO = new UserDAO();
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String EMAIL = "email";
	public static final String MOBILE = "mobile";
	public static final String PASSWORD = "password";
	public static final String UPDATE_SUCCESS = "updatesuccess";
	public static final String UPDATE_FAILURE = "updatefailure";
	public static final String UPDATE_STATUS = "updatestatus";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {
		String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String email = request.getParameter(EMAIL);
        String mobile = request.getParameter(MOBILE);
        String password = request.getParameter(PASSWORD);
        String toPage = "userData.jsp";
        User user = new User(firstName, lastName, email, mobile, password, false, false);
        boolean isUpdated = userDAO.updatetUser(user);
		
        if (isUpdated) { 
        	request.setAttribute(UPDATE_STATUS, UPDATE_SUCCESS);
        	
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/USERDATAGET");
        	dispatcher.forward(request,response);
        }
        else {
        	request.setAttribute(UPDATE_STATUS, UPDATE_FAILURE);
    	    RequestDispatcher dispatcher = request.getRequestDispatcher(toPage);
    	    dispatcher.forward(request, response);
        }
        
	}
}
