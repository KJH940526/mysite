package com.bitacademy.mysite.repository;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.GuestbookVo;


@Repository
public class GuestbookRepository {
	
	@Autowired
	private SqlSession sqlSeesion;
	

	
	public List<GuestbookVo> findAll() {
		System.out.println(sqlSeesion.selectList("guestbook.findAll"));
		return sqlSeesion.selectList("guestbook.findAll");
	}
	
	public int insert(GuestbookVo vo) {
		System.out.println(sqlSeesion.insert("guestbook.insert", vo));
		return sqlSeesion.insert("guestbook.insert", vo);
	}
	
	public int delete(GuestbookVo vo) {
		System.out.println(sqlSeesion.delete("guestbook.delete", vo));
		return sqlSeesion.delete("guestbook.delete", vo);
	}
}
