package com.ssafy.happyhouse.model.service;
import java.util.List;

import com.ssafy.happyhouse.model.dto.CoronaDto;
import com.ssafy.happyhouse.model.dto.HospitalDto;
import com.ssafy.happyhouse.model.dto.HouseDealDto;

public interface HouseDealService {
	public List<HouseDealDto> treadInfo(String dong);
	public List<String> gungu(String city);
	public List<String> dong(String gu);
	public List<HouseDealDto> getReal(String name);
	public List<CoronaDto> getCorona(String gu);
	public List<HospitalDto> getHospital(String gu);
}
