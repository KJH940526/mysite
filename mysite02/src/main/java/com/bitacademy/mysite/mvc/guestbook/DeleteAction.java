package com.bitacademy.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.mysite.repository.GuestbookRepository;
import com.bitacademy.mysite.vo.GuestbookVo;
import com.bitacademy.web.mvc.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String password = request.getParameter("password");
		
		GuestbookVo guestbookVo = new GuestbookVo();
		guestbookVo.setNo(Long.parseLong(no));
		guestbookVo.setPassword(password);
		
		new GuestbookRepository().delete(guestbookVo);
		response.sendRedirect(request.getContextPath() + "/guestbook");

	}

}
