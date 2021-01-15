package com.bitacademy.mysite.repository;


import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSeesion;
	
	public UserVo findByNo(Long userNo) {
		return sqlSeesion.selectOne("user.find",userNo);
	}
	
	public UserVo findByEmail(String email) {
		UserVo userVo = sqlSeesion.selectOne("user.findByEmail", email);
		return userVo;
	}

	public UserVo findByEmailAndPassword(UserVo vo) {
		UserVo userVo = sqlSeesion.selectOne("user.findByEmailAndPassword", vo);
		System.out.println(userVo);
		return userVo;
	}
	
	public UserVo findByEmailAndPassword2(UserVo vo) {
		Map<String, Object> map = new HashMap<>();
		map.put("e", vo.getEmail());
		map.put("p", vo.getPassword());
		UserVo userVo = sqlSeesion.selectOne("user.findByEmailAndPassword2", vo);
		System.out.println(userVo);
		return userVo;
	}

	public int update(UserVo vo) {
		return sqlSeesion.update("user.update", vo);
	}

	public int insert(UserVo userVo) {
		return sqlSeesion.insert("user.insert", userVo);
	}



}
