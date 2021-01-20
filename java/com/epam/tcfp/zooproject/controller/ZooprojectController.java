package com.epam.tcfp.zooproject.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.tcfp.zooproject.service.factory.*;

/**
 * Servlet implementation class Controller
 */
public class ZooprojectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ServiceFactory SERVICE_FACTORY = ServiceFactory.getInstance();
	static Logger logger = LogManager.getLogger();

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI().toLowerCase();

        Service currentService = SERVICE_FACTORY.getService(requestUri);
        try {
          currentService.execute(req, resp);
        } catch (ParseException | SQLException e) {
			logger.log(Level.ERROR, "Exception in Controller");
			logger.info(e.getClass().getName());
			logger.info(e.getMessage());
        }
      }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
