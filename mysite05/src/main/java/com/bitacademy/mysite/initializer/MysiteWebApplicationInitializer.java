package com.bitacademy.mysite.initializer;

import javax.servlet.Filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.bitacademy.mysite.config.AppConfig;
import com.bitacademy.mysite.config.WebConfig;

import ch.qos.logback.core.rolling.helper.FileFilterUtil;

													//mysite04의 web.xml에 AnnotationConfigWebApplicationContext가 있음!
public class MysiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// Root Application Context Config
		System.out.println("Root");
		return new Class<?>[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// Web Application Context Config
		System.out.println("Servlet");
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// DispactcherServlet Mapping
		System.out.println("Mapping");
		return new String[] { "/" };
	}

	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
		// create DispatcherServlet
		System.out.println("Dispatcher");
		DispatcherServlet dispatcherServlet = (DispatcherServlet)super.createDispatcherServlet(servletAppContext);
		
		// Exception Hanler가 없으면 Exception을 던지게끔 설정! default는 flase
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		
		
		return dispatcherServlet;
	}

	@Override
	protected Filter[] getServletFilters() {
		//인코딩 설정
		return new Filter[] { new CharacterEncodingFilter("UTF-8", true) };
	}
	
	
	

}
