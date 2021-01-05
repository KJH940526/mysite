package com.bitacademy.mysite.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.mysite.repository.GuestbookRepository;
import com.bitacademy.mysite.service.GuestbookService;
import com.bitacademy.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("")
	public String index(Model model) { //넘길 객체가 있으면 모델로 넘겨준다!
		List<GuestbookVo> list = guestbookService.getMessageList();
		model.addAttribute("list", list);
		return "guestbook/index";
	}
	
	@RequestMapping("add")
	public String add(GuestbookVo vo) { //웹에서 넘어오는 파라미터를 vo로 받는다!!
		guestbookService.writeMessage(vo);
		//모델에 넘길 필요없음!!
		return "redirect:/guestbook"; //데이터 입력이니깐 리다이렉트!!
	}
	
	//딜리트폼!! get으로 넘어가는!!
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, Model model) { //파라미터 no를 받음!!
		model.addAttribute("no", no); //${no } 때문에 필요!!
		return "guestbook/delete"; 
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(GuestbookVo vo) {
		System.out.println(vo);
		guestbookService.deleteMessage(vo);
		return "redirect:/guestbook"; 
	}
}
