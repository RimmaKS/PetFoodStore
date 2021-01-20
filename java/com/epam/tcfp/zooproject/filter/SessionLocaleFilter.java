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

public class SessionLocaleFilter implements Filter {
	private ServletContext context;
	public static final String SESSION_LOCALE = "sessionLocale";
	public static final String LANG = "lang";
	public static final String FILTER_LOG = "SessionLocaleFilter initialized";
	

	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log(FILTER_LOG);
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		if (req.getParameter(SESSION_LOCALE) != null) {
			req.getSession().setAttribute(LANG, req.getParameter(SESSION_LOCALE));
		}
		chain.doFilter(request, response);
	}

	public void destroy() {

	}
}