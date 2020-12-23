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
			System.out.println("GuestBookActionFactory" + actionName);
			action = new AddAction();
			
		} else if("delete".equals(actionName)) {
			System.out.println("GuestBookActionFactory" + actionName);
			action = new DeleteAction();		
		} else if("deleteform".equals(actionName)) {
			System.out.println("GuestBookActionFactory" + actionName);
			action = new DeleteFormAction();
		} else {
			System.out.println("GuestBookActionFactory" + actionName);
			action = new GuestAction();
		}
		return action;

	}

}


	