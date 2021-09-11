package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.DongReviewDto;

public interface DongReviewService {
	//모든 동의 정보를 반환한다. user랑 조인해서 user의 성향 정보까지 갖고있다.
	public List<Map<String, String>> getReviewAll();
	//map에 있는 조건으로 검사해, 일치하는 조건의 사용자가 쓴 리뷰로만 통계를 내서 별점 정보를 반환한다.
	public List<Map<String, String>> getRatingByType(Map<String, String> map);
		
	//해당 동에 있는 모든 리뷰 반환
	public List<Map<String, String>> getReviewByDong(String dongcode);
	//해당 유저가 쓴 모든 리뷰 반환
	public List<DongReviewDto> getReviewByUser(String userid);
	//랭킹용
	//모든 동에 있는 별점 정보를 반환
	public List<Map<String,String>> getRatingAll();
	//해당 동에 있는 별점 정보를 반환
	public Map<String,String> getRatingByDong(String dongcode);
	
	public boolean insert(DongReviewDto dongReviewDto);
	public boolean delete(String userid);
	public boolean update(DongReviewDto dongReviewDto);

}
