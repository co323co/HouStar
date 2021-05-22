package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

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
	public List<DongReviewDto> getReviewByUser(String userid) {
		return sqlSession.getMapper(DongReviewMapper.class).getReviewByUser(userid);
	}

	@Override
	public List<DongReviewDto> getReviewByDong(String dongcode) {
		return sqlSession.getMapper(DongReviewMapper.class).getReviewByDong(dongcode);
	}

	@Override
	public List<Map<String, String>> getRatingAll() {
		return sqlSession.getMapper(DongReviewMapper.class).getRatingAll();
	}

	@Override
	public Map<String, String> getRatingByDong(String dongcode) {
		return sqlSession.getMapper(DongReviewMapper.class).getRatingByDong(dongcode);
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
