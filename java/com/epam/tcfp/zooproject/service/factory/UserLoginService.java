package com.epam.tcfp.zooproject.service.factory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.tcfp.zooproject.dao.UserDAO;
import com.epam.tcfp.zooproject.entity.User;

public class UserLoginService implements Service{
    private final UserDAO userDAO = new UserDAO();
    public static final String LOGIN_STATUS = "loginStatus";
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String USER = "user";
	public static final String LOGIN_SUCCESS = "loginSuccess";
	public static final String ADMIN_USER = "isAdmin";
	public static final String REFERER_PAGE = "Referer";
	public static final String INCORRECT_CREDENTIALS = "incorrectCredentials";
	public static final String USER_NOT_ACTIVE = "userNotActive";
	public static final String LOGIN_ERROR = "loginError";
    
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {
	    String loginStatus = LOGIN_STATUS;   
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);  
		HttpSession session = request.getSession(true);
		User user = userDAO.selectUser(email);
		
		if(user.isActive()) {		
            if(userDAO.validateUser(email, password)) {    	    	

    			session.setAttribute(USER, email);
    			session.setAttribute(loginStatus,LOGIN_SUCCESS);
    			session.setAttribute(ADMIN_USER, String.valueOf(user.isAdmin()));    			
    			
    			session.setMaxInactiveInterval(30*60);
    			Cookie userName = new Cookie(USER, email);
    			userName.setMaxAge(30*60);
    			response.addCookie(userName);
                request.getRequestDispatcher("index.jsp").forward(request, response);	
            }
            else {
            	session.setAttribute(loginStatus, INCORRECT_CREDENTIALS);
                response.sendRedirect(request.getHeader(REFERER_PAGE));
            }            
		}
		else if(!user.isActive()) {
        	session.setAttribute(loginStatus, USER_NOT_ACTIVE);
            response.sendRedirect(request.getHeader(REFERER_PAGE));
		}
        else {
        	session.setAttribute(loginStatus, LOGIN_ERROR);
            response.sendRedirect(request.getHeader(REFERER_PAGE));
        }
    }  
	}