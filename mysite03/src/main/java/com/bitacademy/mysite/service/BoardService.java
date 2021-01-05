package com.bitacademy.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.mysite.repository.BoardRepository;
import com.bitacademy.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public BoardVo boardView(Long boardNo) {
		return boardRepository.findByNo(boardNo);
	}
	
	public boolean boardUpdate(BoardVo boardVo) {
		return boardRepository.update(boardVo);
	}
	
	public boolean boardInsert(BoardVo boardVo) {
		return boardRepository.insert(boardVo);
	}
	
	public boolean boardDelete(BoardVo boardVo) {
		return boardRepository.delete(boardVo);
	}
	
	public List<BoardVo> boardList(){
		return boardRepository.findAll();
	}
	
	
}
