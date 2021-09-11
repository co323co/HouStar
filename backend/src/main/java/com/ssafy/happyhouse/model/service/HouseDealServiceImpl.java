package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.aop.LoggingAspect;
import com.ssafy.happyhouse.model.dto.CoronaDto;
import com.ssafy.happyhouse.model.dto.HospitalDto;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.mapper.HouseDealMapper;

@Service
public class HouseDealServiceImpl implements HouseDealService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private static HouseDealService housedealservice;

	@Override
	public List<String> gungu(String city) {
		return sqlSession.getMapper(HouseDealMapper.class).gungu(city);
	}

	@Override
	public List<String> dong(String gu) {
		return sqlSession.getMapper(HouseDealMapper.class).dong(gu);
	}
	@Override
	public List<HouseDealDto> treadInfo(String dong) {
		return sqlSession.getMapper(HouseDealMapper.class).treadInfo(dong);
	}
	@Override
	public List<HouseDealDto> getReal(String name) {
		return sqlSession.getMapper(HouseDealMapper.class).getReal(name);
	}
	@Override
	public List<CoronaDto> getCorona(String gu) {
		return sqlSession.getMapper(HouseDealMapper.class).getCorona(gu);
	}
	@Override
	public List<HospitalDto> getHospital(String gu) {
		return sqlSession.getMapper(HouseDealMapper.class).getHospital(gu);
	}

	@Override
	public List<HouseDealDto> getall() {
		
		return sqlSession.getMapper(HouseDealMapper.class).getall();
	}

}
