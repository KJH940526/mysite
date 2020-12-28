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
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		System.out.println("============WrithAction============");
		System.out.println(authUser);
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		// --   					no  , title, contents,date, h, gno,ono,depth,userno 
		// insert into board values(null, '제목1','콘텐츠1',now(), 1, 1,  1,   1,    1);
		// String sql = "insert into board values(null, ?, ?,now(), ?, ?,  ?, ?, ?)";
		
		
		//히트는 카운터를 쓰자!!
		//더미데이터!
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setHit(1L);
		vo.setGroupNo(1L);
		vo.setOrderNo(1);
		vo.setDepth(1);
		vo.setUserNo(authUser.getNo());
		
		System.out.println("==========BoardVo==============");
		System.out.println(vo);
		new BoardRepository().insert(vo);
		System.out.println("글작성");
		
		WebUtil.redirect(request, response, request.getContextPath()+ "/board" );
		
		

	}

}
