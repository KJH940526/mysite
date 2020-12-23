package com.bitacademy.mysite.mvc.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitacademy.mysite.vo.UserVo;
import com.bitacademy.web.mvc.Action;
import com.bitacademy.web.util.WebUtil;

public class MainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int visitCount = 0;
		
		
		// 쿠키읽기
		// javax.servlet.http.Cookie
		Cookie[] cookies = request.getCookies();
		//쿠키가 null이 아니고 존재할때!!
		if(cookies != null && cookies.length >0) {
			for(Cookie cookie : cookies) {
				if("visitCount".equals(cookie.getName())) {
					visitCount = Integer.parseInt(cookie.getValue());
				}
				System.out.println(cookie.getName()+ ":" + cookie.getValue());
			}
		}
		
		
		
		//쿠키 쓰기(굽기)
		visitCount++;
		Cookie cookie = new Cookie("visitCount", String.valueOf(visitCount));
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(24*60*60); // 만료기간 1day
		response.addCookie(cookie);
		
//		// 로그인 되어있는지 안 되어있는지 확인!! 만들지 말고 세션을 받아와야함!!
//		HttpSession session = request.getSession(false);
//		UserVo authUser = session.getAttribute("authUser"); //유저 이름은 일치해야함!!
//		
		WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");

	}

}
