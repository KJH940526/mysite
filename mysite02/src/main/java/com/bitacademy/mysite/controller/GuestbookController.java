package com.bitacademy.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.mysite.mvc.guestbook.GuestbookActionFactory;
import com.bitacademy.web.mvc.Action;
import com.bitacademy.web.mvc.ActionFactory;


public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String actionName = request.getParameter("a");
		
		//액션 패팩토리는 추상클래스
		ActionFactory actionFactory = new GuestbookActionFactory();
		Action action = actionFactory.getAction(actionName);
        action.execute(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
