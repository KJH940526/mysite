package com.bitacademy.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitacademy.mysite.repository.GuestbookRepository;
import com.bitacademy.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookRepository guestbookRepository;
	
	@RequestMapping("")
	public String index(Model model) { //넘길 객체가 있으면 모델로 넘겨준다!
		List<GuestbookVo> list = guestbookRepository.findAll();
		model.addAttribute("list", list);
		return "guestbook/index";
	}
}
