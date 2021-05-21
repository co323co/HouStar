package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.dto.DongReviewDto;

public interface DongReviewMapper {
	// 1. 동별 총 평균 계산 
		public Double getTotalAvg(String dongcode);
		// 2. 동의 항목별 평균 계산
		public Double getInfraAvg(String dongcode);
		public Double getTransAvg(String dongcode);
		public Double getSaftyAvg(String dongcode);
		public Double getHealthAvg(String dongcode);
		public Double getSchoolAvg(String dongcode);
		public Double getEnvironmentAvg(String dongcode);

		public DongReviewDto getDongReview(String userid);
		// 3. 리뷰 총 몇명이 썼는지=> get으로나중에 count하기
		public List<DongReviewDto> getDongReviewList(String dongcode);
		public boolean insert(DongReviewDto dongReviewDto);
		public boolean delete(String userid);
		public boolean update(DongReviewDto dongReviewDto);
}
