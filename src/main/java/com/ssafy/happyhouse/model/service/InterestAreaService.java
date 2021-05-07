package com.ssafy.model.service;

import java.util.List;

import com.ssafy.model.InterestArea;

public interface InterestAreaService {
	
	public void insertArea(InterestArea interestArea );
	public void deleteArea(String code, String userId);
	public List<String> findArea (String userid);
	
}
