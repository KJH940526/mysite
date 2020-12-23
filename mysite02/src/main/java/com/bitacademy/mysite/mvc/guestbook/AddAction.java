package com.bitacademy.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.mysite.repository.GuestbookRepository;
import com.bitacademy.mysite.vo.GuestbookVo;
import com.bitacademy.web.mvc.Action;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name"); // 이름은 똑같게 하기
		String password = request.getParameter("password");
		String message = request.getParameter("message");

		GuestbookVo guestbookVo = new GuestbookVo();
		
		guestbookVo.setName(name);
		guestbookVo.setPassword(password);
		guestbookVo.setMessage(message);
		
//		System.out.println("GuestBook AddAction Get Name : " + guestbookVo.getName());
//		System.out.println("GuestBook AddAction Get PW: " + guestbookVo.getPassword());
//		System.out.println("GuestBook AddAction Get Vo: " + guestbookVo);
		
		new GuestbookRepository().insert(guestbookVo);
		
		response.sendRedirect(request.getContextPath() + "/guestbook");
	}

}
