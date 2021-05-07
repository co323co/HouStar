package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

@RestController
@RequestMapping("housemap")
public class HouseMapController {

	@Autowired
	HouseMapService hmSer;
	
	@GetMapping("sido")
	List<SidoGugunCodeDto> getSido() throws Exception {
		return hmSer.getSido();
	}
	
	//시도 코드 (앞 두글자) ex) 서울 : 11
	@GetMapping("gugun/{sido}")
	List<SidoGugunCodeDto> getGugun(@PathVariable("sido") String sido) throws Exception {
		System.out.println(sido);
		return hmSer.getGugunInSido(sido);
	}
	
	//Deal거래가 있는 동만 불러오기 위해 HouseDeal로 반환함
	@GetMapping("dong/{gugun}")
	List<HouseInfoDto> getDong(@PathVariable("gugun") String gugun) throws Exception {
		return hmSer.getDongInGugun(gugun);
	}
	
}
