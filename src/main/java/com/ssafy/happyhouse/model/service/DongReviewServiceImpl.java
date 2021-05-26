package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
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
	public List<Map<String, String>> getReviewByDong(String dongcode) {
		//연령대가 0이면 선택 안한거니 null로 바꿔준다.
		List<Map<String ,String>> res = sqlSession.getMapper(DongReviewMapper.class).getReviewByDong(dongcode);
		for (Map<String, String> map : res) {
			if(map.get("age_range").equals("0")) map.put("age_range", null);
		}
		return res;
	}

	@Override
	public List<Map<String, String>> getRatingAll() {
		return sqlSession.getMapper(DongReviewMapper.class).getRatingAll();
	}

	@Override
	public Map<String, String> getRatingByDong(String dongcode) {
		Map<String, String> res = sqlSession.getMapper(DongReviewMapper.class).getRatingByDong(dongcode);
		//만약 리뷰가 0개여서 평균 정보를 반환하지 못하면 0으로 찍어준다.
		if(res==null) {
			res = new HashMap<String, String>();
			res.put("total", "0");
			res.put("environment", "0");
			res.put("school", "0");
			res.put("safety", "0");
			res.put("dongcode", dongcode);
			res.put("trans", "0");
			res.put("health", "0");
			res.put("infra", "0");
		}
		return res;
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

	@Override
	public List<Map<String, String>> getReviewAll() {
		//연령대가 0이면 선택 안한거니 null로 바꿔준다.
		List<Map<String ,String>> res = sqlSession.getMapper(DongReviewMapper.class).getReviewAll();
		for (Map<String, String> map : res) {
			if(map.get("age_range").equals("0")) map.put("age_range", null);
		}
		return res;
	}

	@Override
	public List<Map<String, String>> getRatingByType(Map<String, String> map) {
		return  sqlSession.getMapper(DongReviewMapper.class).getRatingByType(map);
	}
}
