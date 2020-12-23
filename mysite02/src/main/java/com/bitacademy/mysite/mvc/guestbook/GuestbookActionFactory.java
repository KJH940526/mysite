package com.bitacademy.mysite.mvc.guestbook;

import com.bitacademy.mysite.mvc.main.MainAction;
import com.bitacademy.web.mvc.Action;
import com.bitacademy.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		// TODO Auto-generated method stub
		Action action = null;
		
		if("add".equals(actionName)) {
//			System.out.println("GuestBookActionFactory ADD");
			action = new AddAction();
		} else {
			action = new GuestAction();
		}
		
		
		return action;

	}

}

/*
	else if ("delete".equals(actionName)) {
	
	String no = (String)request.getAttribute("no");
	String password = request.getParameter("password");
	GuestbookVo vo = new GuestbookVo();
	vo.setNo(Long.parseLong(no));
	vo.setPassword(password);
	new GuestbookDao().delete(vo);
	response.sendRedirect(request.getContextPath() + "/gb");
	

} else if ("deleteform".equals(actionName)) {
	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
	rd.forward(request, response);
	
		
} else {
	

}
response.setContentType("text/html; charset=utf-8");

}
*/