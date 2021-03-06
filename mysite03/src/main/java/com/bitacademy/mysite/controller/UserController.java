package com.bitacademy.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.mysite.service.UserService;
import com.bitacademy.mysite.vo.UserVo;
import com.bitacademy.security.Auth;
import com.bitacademy.security.AuthUser;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	//조인 폼
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) { //spring form에서 모델을 받기떄문!
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) { //값을 받아서 넣어야해서!!
		if(result.hasErrors()) {
//			List<ObjectError> list = result.getAllErrors();
//			for(ObjectError error : list) {
//				System.out.println(error);
//			}
			
			model.addAttribute("userVO", userVo); //@ModelAttribute는 이걸 대신해줌
			model.addAllAttributes(result.getModel());			
			return "user/join";
		}
		System.out.println(userVo);
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}
	
	//로긴 폼!!
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
//	// modelattribute는 userVo라는 이름으로 자동으로 넣어줌
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String login(HttpSession session, @ModelAttribute UserVo userVo) { //값을 받아서 넣어야해서!!
//		UserVo authUser = userService.getUser(userVo);	
//		System.out.println(authUser); //비밀번호가 틀리면 null이 나옴!!
//		if(authUser == null) {
//			return "user/login";
//		}
//		
//		// 인증처리!!
//		session.setAttribute("authUser", authUser);
//		return "redirect:/";
//	}
	
//	//로그아웃
//	@RequestMapping(value = "/logout")
//	public String logout(HttpSession session) { 
//		// ACL 접근제어!!  //인증되었을때만 들어와야해서!!
//		UserVo authUser = (UserVo) session.getAttribute("authUser");
//		if(authUser == null) {
//			return "redirect:/";
//		}
//		
//		// 로그아웃처리
//		session.removeAttribute("authUser");
//		session.invalidate();
//		
//		return "redirect:/";
//	}
//	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		System.out.println("조인 석세스");
		return "user/joinsuccess";
	}
	
	//업데이트 폼
	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {
		System.out.println(authUser);
		
		//정보를 업데이트 폼에 넘겨줘야함!! 페이지에서 정보를 꺼내야함!!
		Long no = authUser.getNo();
		UserVo userVo = userService.getUser(no);
		
		model.addAttribute("vo", userVo); //vo라는 이름으로 넘김!!
		return "user/update";
	}
	
	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, UserVo userVo) { //파라미터로 받은애를 써야함!
						//세션에서 가져오는값,          //폼에서 가져오는 값

		Long no = authUser.getNo();
		userVo.setNo(no);  // no를 셋팅해야함!!
		
		authUser.setName(userVo.getName());
		
		userService.updateUser(userVo);
		return "redirect:/user/update";
	}
	
	
}
