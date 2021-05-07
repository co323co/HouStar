package com.ssafy.happyhouse.model.service;
import java.util.List;

import com.ssafy.model.CoronaDto;
import com.ssafy.model.Hospital;
import com.ssafy.model.HouseDealDto;


public interface HousedealService {
	public List<HouseDealDto> treadInfo(String dong);
	public List<String> gungu(String city);
	public List<String> dong(String gu);
	public List<HouseDealDto> getReal(String name);
	public List<CoronaDto> getCorona(String gu);
	public List<Hospital> getHospital(String gu);
}
