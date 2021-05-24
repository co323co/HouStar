package com.ssafy.happyhouse.model.service;

import java.util.Map;

import com.ssafy.happyhouse.model.dto.CrimeRankDto;

public interface CrimeRankService {
	public CrimeRankDto getCrimeRank(Map<String,String> map);
}
