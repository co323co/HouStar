package com.ssafy.happyhouse.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.CrimeRankDto;
import com.ssafy.happyhouse.model.mapper.CrimeRankMapper;


@Service
public class CrimeRankServiceImpl implements CrimeRankService {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public CrimeRankDto getCrimeRank(Map<String, String> map) {
		return sqlSession.getMapper(CrimeRankMapper.class).getCrimeRank(map);
	}

}
