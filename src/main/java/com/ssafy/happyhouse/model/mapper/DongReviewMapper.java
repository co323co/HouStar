package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.DongReviewDto;

public interface DongReviewMapper {
	//해당 동에 있는 모든 리뷰 반환
	public List<DongReviewDto> getReviewByDong(String dongcode);
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
