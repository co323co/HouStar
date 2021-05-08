package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.MartDto;
import com.ssafy.happyhouse.model.mapper.MartMapper;

@Service
public class MartServicelmpl implements MartService {
	
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private static MartService martService;
	
	@Override
	public List<MartDto> getinfo(String dong) {		
		return sqlSession.getMapper(MartMapper.class).getinfo(dong);
	}
	

}
