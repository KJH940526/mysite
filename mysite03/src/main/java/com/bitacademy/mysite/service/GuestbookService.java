package com.bitacademy.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.mysite.repository.GuestbookRepository;
import com.bitacademy.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookRepository guestbookRepository;

	public List<GuestbookVo> getMessageList() {
		return guestbookRepository.findAll();
	}

	public void writeMessage(GuestbookVo vo) {
		System.out.println("before----> : " +vo);
		guestbookRepository.insert(vo);
		System.out.println("after----> : " + vo);
		//guestbook.xml에서 selectKey를 지정했기떄문에!! 아니면 null이 나옴!!
		//https://yookeun.github.io/java/2014/07/11/mybatis-selectkey/
	}

	public void deleteMessage(GuestbookVo vo) {
		guestbookRepository.delete(vo);
	};
	
	
	
}
