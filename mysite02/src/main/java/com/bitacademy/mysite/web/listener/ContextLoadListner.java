package com.bitacademy.mysite.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoadListner implements ServletContextListener {


    public ContextLoadListner() { //기본 생성자!
        // TODO Auto-generated constructor stub
    }

    public void contextInitialized(ServletContextEvent servletContextEvent)  {
    	ServletContext context = servletContextEvent.getServletContext();
    	String contextConfigLocation = context.getInitParameter("contextConfigLocation");
        System.out.println("Application Start : " + contextConfigLocation);
   }
    
    public void contextDestroyed(ServletContextEvent servletContextEvent)  { 
         // TODO Auto-generated method stub
    }



	
}
