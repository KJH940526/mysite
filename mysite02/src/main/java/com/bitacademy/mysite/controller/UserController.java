package com.bitacademy.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.mysite.repository.UserRepository;
import com.bitacademy.mysite.vo.UserVo;
import com.bitacademy.web.mvc.WebUtil;


public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// actionName.equlas("joinform")인 이유는 actionName이 null일 수가 있기 때문에!!
		// 아래방법은 없으면 빈스트링이 나옴!!
		String actionName = request.getParameter("a");
		if("joinform".equals(actionName)) {
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinform.jsp");
		} else if("joinsuccess".equals(actionName)) {
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinsuccess.jsp");
		} else if("join".equals(actionName)) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			UserVo userVo = new UserVo();
			userVo.setName(name);
			userVo.setEmail(email);
			userVo.setPassword(password);
			userVo.setGender(gender);
			
			new UserRepository().insert(userVo);
			
			System.out.println(userVo);
			//리다이렉트로 또 들어가는거 막아줘야함!!
			WebUtil.redirect(request, response, request.getContextPath() + "/user?a=joinsuccess");
			
		} else { //내가 요청한곳이 아니면 메인으로 보냄  => user?sdfksdfjsdfi 이런주소!
			WebUtil.redirect(request, response, request.getContextPath());
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
