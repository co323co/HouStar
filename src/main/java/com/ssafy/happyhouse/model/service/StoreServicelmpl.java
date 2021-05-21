package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.StoreDto;
import com.ssafy.happyhouse.model.mapper.StoreMapper;

@Service
public class StoreServicelmpl implements StoretService {
	
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private static StoretService martService;
	
	@Override
	public List<StoreDto> getinfo(String dong) {		
		return sqlSession.getMapper(StoreMapper.class).getinfo(dong);
	}
	

}
