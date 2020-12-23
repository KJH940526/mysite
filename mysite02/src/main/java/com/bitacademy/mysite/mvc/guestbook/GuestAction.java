package com.bitacademy.mysite.mvc.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.mysite.repository.GuestbookRepository;
import com.bitacademy.mysite.vo.GuestbookVo;
import com.bitacademy.web.mvc.Action;
import com.bitacademy.web.util.WebUtil;

public class GuestAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<GuestbookVo> list = new GuestbookRepository().findAll(); // 컨트롤하고 이제 뷰로 넘겨야함!
		request.setAttribute("list", list);
		WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
		
	}

}
