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
	public int insert(Map<String, String> map) {	//String userid, String code
		return sqlSession.getMapper(InterestAreaMapper.class).insert(map);
	}

	@Override
	public int delete(Map<String, String> map) {	//String code, String userid
		return sqlSession.getMapper(InterestAreaMapper.class).delete(map);
	}

	@Override
	public List<InterestAreaDto> list(String userid) {
		return sqlSession.getMapper(InterestAreaMapper.class).list(userid);
	}

	@Override
	public String addressToCode(Map<String, String> map) {
		return sqlSession.getMapper(InterestAreaMapper.class).addressToCode(map);
	}
}
