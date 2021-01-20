package com.epam.tcfp.zooproject.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {
	public static final String FILTER_LOG = "AuthenticationFilter initialized";
	public static final String UNAUTORIZED_LOG = "Unauthorized  admin access request";
	public static final String REQUESTED_RESOURSE = "Requested Resource::";
	public static final String USER = "user";
	public static final String GUEST = "guest";
	public static final String LOGIN_STATUS = "loginStatus";
	public static final String LOGIN_GUEST = "loginguest";
	public static final String ADMIN_USER = "isAdmin";
	public static final String FALSE = "false";
	
	private ServletContext context;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log(FILTER_LOG);
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String toPage = "userLogin.jsp";
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		this.context.log(REQUESTED_RESOURSE + uri);
		
		HttpSession session = ((HttpServletRequest) request).getSession(false);
		
		if (session == null) {
		    session = ((HttpServletRequest) request).getSession();
			session.setAttribute(USER, GUEST);
			session.setAttribute(LOGIN_STATUS, LOGIN_GUEST);
			session.setAttribute(ADMIN_USER, FALSE);  
		}

		String userRole = (String) session.getAttribute(ADMIN_USER);			
		
		if (uri.contains("admin") && userRole.equals(FALSE)) {	
			this.context.log(UNAUTORIZED_LOG);
			res.sendRedirect(toPage);
		}
		else {
			chain.doFilter(request, response);
		}		
	}

	public void destroy() {
	}

}
