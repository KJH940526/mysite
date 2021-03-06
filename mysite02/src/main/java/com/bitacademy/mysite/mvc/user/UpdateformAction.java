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

public class UpdateformAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 세션에서 authUser 가져오기
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		System.out.println(authUser);
		
		// 2. authUser에서 no 가져오기
		System.out.println(authUser.getNo());
		// 3. no를 가지고 Repository를 통해 UserVo 가져오기!
		UserVo userVo = new UserRepository().findNo(authUser.getNo());
		System.out.println("updateformAction : "+userVo);
		request.setAttribute("vo", userVo);
		
		// 4. jsp로 UserVo 전달하면서 FORWADING 하기
		WebUtil.forward(request, response, "/WEB-INF/views/user/updateform.jsp");
	}

}
