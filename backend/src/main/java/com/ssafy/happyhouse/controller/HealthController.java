package com.ssafy.happyhouse.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.CoronaDto;
import com.ssafy.happyhouse.model.dto.HospitalDto;
import com.ssafy.happyhouse.model.service.HouseDealService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Health API")
@RestController
@CrossOrigin("*")
@RequestMapping("api/health")
public class HealthController {
	
	// 여기에 코로나 , 병원정보 가져오는 method 존재.
	@Autowired
	HouseDealService houseDealService;
	
	@ApiOperation("gugunCode로 해당 구의 안심병원 리스트를 가져온다. ex 11110 ( 종로구 ) ")
	@GetMapping("/hospitals/{gugunCode}")
	public List<HospitalDto> getHospital(@PathVariable String gugunCode){	
		return houseDealService.getHospital(gugunCode);
	}
	
	@ApiOperation("gugunCode로 해당 구의 코로나 선별진료소 리스트를 가져온다. ex 11110 ( 종로구 )")
	@GetMapping("/coronas/{gugunCode}")
	public List<CoronaDto> getCorona(@PathVariable String gugunCode){	
		return houseDealService.getCorona(gugunCode);
	}
}