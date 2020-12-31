package com.bitacademy.mysite.mvc.board;

import com.bitacademy.web.mvc.Action;
import com.bitacademy.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("write".equals(actionName)) {		
			action = new WrithFormAction();
		} else if ("writeaction".equals(actionName)) {
			action = new WrithAction();
		} else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if ("view".equals(actionName)) {
			System.out.println("겟액션 뷰");
			action = new ViewAction();
		}else if ("update".equals(actionName)) {
			System.out.println("겟액션 업데이트");
			action = new UpdateFormAction();
		}else if ("updateaction".equals(actionName)) {
			System.out.println("겟액션 업데이트액션");
			action = new UpdateAction();
		}else {
			action = new ListAction();
		}
		
		return action;
	}

}