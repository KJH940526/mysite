package com.bitacademy.mysite.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class EncodingFilter implements Filter {
	private String encoding;
	
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
		if(null == encoding) {
			encoding = "utf-8";
		}
	}
	
	//filter는 url 맵핑할떄 모든 경로를 해주기 위해서는 /*로 맵핑을 해야한다.
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// request 처리
		request.setCharacterEncoding(encoding);
		
		chain.doFilter(request, response);
		
		// response 처리=> 처리할게 있다면!!
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
	}





}
