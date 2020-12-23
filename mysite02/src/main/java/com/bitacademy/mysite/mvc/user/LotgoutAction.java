package com.bitacademy.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitacademy.web.mvc.Action;
import com.bitacademy.web.util.WebUtil;

public class LotgoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션을 기존에 있는것을 준다!!
		HttpSession session = request.getSession(false);
		
		if(session != null) {  //로그인하고 세션을 받은경우!!
			session.removeAttribute("authUser"); //세션삭제!!
			session.invalidate();
		}
		
		//로그인도 안하고 바로 user로 들어온 경우는 세션이 널임!
		WebUtil.redirect(request, response, request.getContextPath());
	}

}
