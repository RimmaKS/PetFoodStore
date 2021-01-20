package com.epam.tcfp.zooproject.service.factory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLogoutService implements Service {
    public static final String STATUS = "status";
	public static final String LOGOUT_SUCCESS = "logoutsuccess";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, SQLException {
    	request.setAttribute(STATUS, LOGOUT_SUCCESS);
        request.getSession().invalidate();
        response.sendRedirect("index.jsp");
	    }
	}
