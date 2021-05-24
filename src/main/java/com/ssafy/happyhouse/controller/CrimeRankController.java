package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.CrimeRankDto;
import com.ssafy.happyhouse.model.service.CrimeRankService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("DongReview API")
@RestController
@CrossOrigin("*")
@RequestMapping("api/crimerank")
public class CrimeRankController {
	
	@Autowired
	CrimeRankService cSer;
	
	@ApiOperation("시도 시군구 이름으로 안전등급dto를 반환한다.")
	@GetMapping("{sidoName}/{gugunName}")
	public CrimeRankDto getCrimeRank(@PathVariable String sidoName,@PathVariable String gugunName){
		Map<String, String> map = new HashMap<>();
		map.put("sidoName", sidoName);
		map.put("gugunName", gugunName);
		return cSer.getCrimeRank(map);
	}
	
	
}
