package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.model.InterestArea;

public interface InterestAreaDAO {
	
	public void insertArea(InterestArea interestArea );
	public void deleteArea(String code, String userId);
	public List<String> findArea (String userId);
	
	
}
