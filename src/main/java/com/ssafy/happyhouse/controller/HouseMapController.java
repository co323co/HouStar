package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunDongDto;
import com.ssafy.happyhouse.model.service.SidoGugunDongService;

//시군구 코드, 주소 변환 등 여기서 처리함
@RestController
@RequestMapping("housemap")
public class HouseMapController {

	@Autowired
	SidoGugunDongService sgdSer;
	
	@GetMapping("sido")
	List<SidoGugunDongDto> getSido() throws Exception {
		return sgdSer.getSido();
	}
	
	//시도 코드 (앞 두글자) ex) 서울 : 11
	@GetMapping("gugun/{sido}")
	List<SidoGugunDongDto> getGugun(@PathVariable("sido") String sido) throws Exception {
		System.out.println(sido);
		return sgdSer.getGugunInSido(sido);
	}
	
	//Deal거래가 있는 동만 불러오기 위해 HouseDeal로 반환함
	@GetMapping("dong/{gugun}")
	List<HouseInfoDto> getDong(@PathVariable("gugun") String gugun) throws Exception {
		return sgdSer.getDongInGugun(gugun);
	}
	
}
