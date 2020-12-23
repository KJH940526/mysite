package com.bitacademy.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitacademy.mysite.repository.UserRepository;
import com.bitacademy.mysite.vo.UserVo;
import com.bitacademy.web.mvc.Action;
import com.bitacademy.web.util.WebUtil;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password= request.getParameter("password");
		
		UserVo vo = new UserVo();
		vo.setEmail(email);
		vo.setPassword(password);
		
		UserVo userVo = new UserRepository().findByEmailAndPassword(vo);
		System.out.println(userVo);
		
		// 틀렷을 경우!!
		if(userVo == null) {
			request.setAttribute("email", email);
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			return;  //프로그램 흐름을 종료해줘야함!! ** 아래로 프로그램이 흘러가게 됨!!
		}
		
		/* 로그인 처리*/ //쿠키를 알아야한다!!
											//세션이 없으면 만들어준다! (true)
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", userVo);
		
		WebUtil.redirect(request, response, request.getContextPath());
		
	}

}
