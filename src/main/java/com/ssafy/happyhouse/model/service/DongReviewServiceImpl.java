package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.DongReviewDto;
import com.ssafy.happyhouse.model.mapper.DongReviewMapper;

@Service
public class DongReviewServiceImpl implements DongReviewService {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Double getTotalAvg(String dongcode) {
		return sqlSession.getMapper(DongReviewMapper.class).getTotalAvg(dongcode);
	}

	@Override
	public Double getInfraAvg(String dongcode) {
		return sqlSession.getMapper(DongReviewMapper.class).getInfraAvg(dongcode);
	}

	@Override
	public Double getTransAvg(String dongcode) {
		return sqlSession.getMapper(DongReviewMapper.class).getTransAvg(dongcode);
	}

	@Override
	public Double getSaftyAvg(String dongcode) {
		return sqlSession.getMapper(DongReviewMapper.class).getSaftyAvg(dongcode);
	}

	@Override
	public Double getHealthAvg(String dongcode) {
		return sqlSession.getMapper(DongReviewMapper.class).getHealthAvg(dongcode);
	}

	@Override
	public Double getSchoolAvg(String dongcode) {
		return sqlSession.getMapper(DongReviewMapper.class).getSchoolAvg(dongcode);
	}

	@Override
	public Double getEnvironmentAvg(String dongcode) {
		return sqlSession.getMapper(DongReviewMapper.class).getEnvironmentAvg(dongcode);
	}

	@Override
	public DongReviewDto getDongReview(String userid) {
		return sqlSession.getMapper(DongReviewMapper.class).getDongReview(userid);
	}

	@Override
	public List<DongReviewDto> getDongReviewList(String dongcode) {
		return sqlSession.getMapper(DongReviewMapper.class).getDongReviewList(dongcode);
	}

	@Override
	public boolean insert(DongReviewDto dongReviewDto) {
		return sqlSession.getMapper(DongReviewMapper.class).insert(dongReviewDto);
	}

	@Override
	public boolean delete(String userid) {
		return sqlSession.getMapper(DongReviewMapper.class).delete(userid);
	}

	@Override
	public boolean update(DongReviewDto dongReviewDto) {
		return sqlSession.getMapper(DongReviewMapper.class).update(dongReviewDto);
	}

}
