package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.InterestAreaDto;

public interface InterestAreaMapper {
	
	public int insert(Map<String, String> map);	//String userId, String code
	public int delete(Map<String, String> map);	//String code, String userId
	public List<InterestAreaDto> list (String userId);
	public String addressToCode (Map<String, String> map); //sido, gugun, dong 	=> code 반환
	
}
