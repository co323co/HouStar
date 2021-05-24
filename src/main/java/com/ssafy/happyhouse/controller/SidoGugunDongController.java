package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunDongDto;
import com.ssafy.happyhouse.model.service.SidoGugunDongService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api("시군구 코드, 주소 변환 API")
@RestController
@CrossOrigin("*")
@RequestMapping("api/address/")
public class SidoGugunDongController {

	@Autowired
	SidoGugunDongService sgdSer;
	
	@ApiOperation("동 코드로 해당 동의 위도, 경도 정보를 반환한다")
	@GetMapping("/pos/dong/{dongcode}")
	Map<String, String> getDongPos(@PathVariable String dongcode) {
		return sgdSer.getDongPos(dongcode);
	}
	
	@ApiOperation("모든 sido code와 시 이름을 받아온다.")
	@GetMapping("sidos")
	List<SidoGugunDongDto> getSido() throws Exception {
		return sgdSer.getSido();
	}
	
	@ApiOperation("sido code (11) (서울) 로 gugunCode, gugunName(종로구)을 반환한다.")
	@GetMapping("guguns/{sido}")
	List<SidoGugunDongDto> getGugun(@PathVariable("sido") String sido) throws Exception {
		System.out.println(sido);
		return sgdSer.getGugunInSido(sido);
	}
	
	@ApiOperation("거래내역이 있는 dong들만 불러오기 위해 houseinfo table에서 select code(11110) 하여  dong(견지동 교남동..)을 반환한다.")
	@GetMapping("dealdong/{gugun}")
	List<HouseInfoDto> getDonghasDeal(@PathVariable("gugun") String gugun) throws Exception {
		return sgdSer.getDongInGugunhasDeal(gugun);
	}
	@ApiOperation("gugncode로 dong리스트 반환용, SidoGugunDongDto list 반환")
	@GetMapping("dongs/{guguncode}")
	List<SidoGugunDongDto> getDongByGugun(@PathVariable("guguncode") String guguncode)throws Exception{		
		Map<String, Object> param = new HashMap<>();
		param.put("guguncode", guguncode);
		return sgdSer.getDongByGugun(guguncode);
	}
	
	@ApiOperation("dongcode로 SidoGugunDongDto 반환") 
	@GetMapping("/{dongcode}")
	SidoGugunDongDto getDong(@PathVariable String dongcode) {
		return sgdSer.getDong(dongcode);
	}
	
}
