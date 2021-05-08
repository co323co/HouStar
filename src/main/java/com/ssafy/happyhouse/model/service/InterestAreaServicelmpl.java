package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.InterestAreaDto;
import com.ssafy.happyhouse.model.mapper.InterestAreaMapper;

@Service
public class InterestAreaServicelmpl implements InterestAreaService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(InterestAreaDto interestArea) {	
		return sqlSession.getMapper(InterestAreaMapper.class).insert(interestArea);
	}

	@Override
	public int delete(Map<String, String> map) {	//String code, String userId
		return sqlSession.getMapper(InterestAreaMapper.class).delete(map);
	}

	@Override
	public List<InterestAreaDto> list(String userId) {
		return sqlSession.getMapper(InterestAreaMapper.class).list(userId);
	}
}
