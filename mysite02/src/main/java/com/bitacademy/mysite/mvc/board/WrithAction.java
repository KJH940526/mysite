package com.bitacademy.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitacademy.mysite.repository.BoardRepository;
import com.bitacademy.mysite.vo.BoardVo;
import com.bitacademy.mysite.vo.UserVo;
import com.bitacademy.web.mvc.Action;
import com.bitacademy.web.util.WebUtil;

public class WrithAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		System.out.println("============WrithAction============");
		System.out.println(authUser);


		if (authUser != null) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			BoardVo vo = new BoardVo();
			vo.setContents(content);
			vo.setTitle(title);
			vo.setUserNo(authUser.getNo());
			vo.setUserName(authUser.getName());
			vo.setHit(0L);
			vo.setGroupNo(1L);
			vo.setOrderNo(1);
			vo.setDepth(1);
			System.out.println("==========BoardVo==============");
			System.out.println(vo);
			new BoardRepository().insert(vo);
		}
		
		System.out.println("글작성");

		WebUtil.redirect(request, response, request.getContextPath() + "/board");

	}

}
