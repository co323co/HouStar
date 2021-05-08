package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.EnvInfoDto;

@Service
public class EnvGuideCheckServiceImpl implements EnvGuideCheckService{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<EnvInfoDto> list() {
		return sqlSession.getMapper(EnvGuideCheckService.class).list();
	}

	@Override
	public List<EnvInfoDto> listByDong(String dongCode) {
		return sqlSession.getMapper(EnvGuideCheckService.class).listByDong(dongCode);
	}

}
