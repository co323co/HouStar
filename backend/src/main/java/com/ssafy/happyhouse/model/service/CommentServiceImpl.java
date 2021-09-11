package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.dto.CommentDto;
import com.ssafy.happyhouse.model.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<CommentDto> listByBid(int bid) {
		return sqlSession.getMapper(CommentMapper.class).listByBid(bid);
	}

	@Override
	public CommentDto select(Map<String, Integer> map) {
		return sqlSession.getMapper(CommentMapper.class).select(map);
	}

	@Override
	public boolean insert(CommentDto comment) {
		if(sqlSession.getMapper(CommentMapper.class).insert(comment)!=0) return true;
		return false;
	}


	@Override
	public boolean update(CommentDto comment) {
		if(sqlSession.getMapper(CommentMapper.class).update(comment)!=0) return true;
		return false;
	}

	@Override
	public boolean delete(Map<String, Integer> map) {
		if(sqlSession.getMapper(CommentMapper.class).delete(map)!=0) return true;
		return false;
	}

}
