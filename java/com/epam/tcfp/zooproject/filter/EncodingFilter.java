package com.epam.tcfp.zooproject.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{
	private static final String CODE_ENCODING = "UTF-8";
	private static final String CONFIG_CONTEXT_TYPE = "text/html; charset=UTF-8";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		response.setContentType(CONFIG_CONTEXT_TYPE);
	    request.setCharacterEncoding(CODE_ENCODING);
	    response.setCharacterEncoding(CODE_ENCODING);
	    
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
