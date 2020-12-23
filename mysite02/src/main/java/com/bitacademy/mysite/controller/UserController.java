package com.bitacademy.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.mysite.mvc.user.UserActionFactory;
import com.bitacademy.web.mvc.Action;
import com.bitacademy.web.mvc.ActionFactory;



public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// actionName.equlas("joinform")인 이유는 actionName이 null일 수가 있기 때문에!!
		// 아래방법은 없으면 빈스트링이 나옴!!
		String actionName = request.getParameter("a");
		
		//액션 팩토리는 추상클래스
		ActionFactory actionFactory = new UserActionFactory();
		Action action = actionFactory.getAction(actionName);
		action.execute(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
