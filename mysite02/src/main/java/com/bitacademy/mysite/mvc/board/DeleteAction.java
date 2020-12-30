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

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if (authUser != null) {
			
			String userName = request.getParameter("name");
			long no = Long.parseLong(request.getParameter("no"));
			
			
			BoardVo vo = new BoardVo();
			vo.setUserName(userName);
			vo.setNo(no);
			System.out.println("==========BoardVo==============");
			System.out.println(vo);
			new BoardRepository().delete(vo);
		}
		
		System.out.println("글작성");

		WebUtil.redirect(request, response, request.getContextPath() + "/board");

	

	}

}
