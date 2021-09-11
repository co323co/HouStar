package com.ssafy.happyhouse.model.mapper;

import java.util.Map;

import com.ssafy.happyhouse.model.dto.CrimeRankDto;

public interface CrimeRankMapper {
	public CrimeRankDto getCrimeRank(Map<String,String> map);
}
