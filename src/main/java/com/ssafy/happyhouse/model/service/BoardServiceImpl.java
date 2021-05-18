package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.mapper.BoardMapper;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;

import io.swagger.annotations.ApiOperation;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<BoardDto> listByBid(Map<String, String> map) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("gubun", map.get("gubun") == null ? "" : map.get("gubun"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		return sqlSession.getMapper(BoardMapper.class).listByBid(param);
	}

	@Override
	public BoardDto select(int id) {
		return sqlSession.getMapper(BoardMapper.class).select(id);
	}

	@Override
	public boolean insert(BoardDto board) {
		if(sqlSession.getMapper(BoardMapper.class).insert(board)!=0) return true;
		return false;
	}


	@Override
	public boolean update(BoardDto board) {
		if(sqlSession.getMapper(BoardMapper.class).update(board)!=0) return true;
		return false;
	}

	@Override
	public boolean delete(int id) {
		if(sqlSession.getMapper(BoardMapper.class).delete(id)!=0) return true;
		return false;
	}

	@Override
	public String findNameByGubun(int gubun) {
		return sqlSession.getMapper(BoardMapper.class).findNameByGubun(gubun);
	}

}
