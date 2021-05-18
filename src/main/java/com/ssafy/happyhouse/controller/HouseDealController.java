package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.dto.CoronaDto;
import com.ssafy.happyhouse.model.dto.HospitalDto;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.service.HouseDealService;
import com.ssafy.happyhouse.model.service.SidoGugunDongService;

@Controller
@RequestMapping("housedeal")
public class HouseDealController {

	@Autowired
	HouseDealService houseDealService;
	// index.jsp에서 pathvalue를 갖고가는경우
	@GetMapping("list/{dong}/{gugun}")
	ModelAndView mvHouseDeal(@PathVariable("dong") String dong, 
			@PathVariable("gugun") String gugun,
			ModelAndView mv)  {
		List<HouseDealDto> tlist = houseDealService.treadInfo(dong);	
		// baseaddress의 dongcode = 위의 gugun .
		// 같은 구 내의 코로나진료소 가져온다.
		List<CoronaDto> clist = houseDealService.getCorona(gugun);
		// 같은 구 내의 안심 병원 가져온다.
		List<HospitalDto> hlist = houseDealService.getHospital(gugun);
		mv.addObject("tlist",tlist);
		mv.addObject("clist",clist);
		mv.addObject("hlist",hlist);
		mv.addObject("dong",dong);
		mv.addObject("gugun",gugun);
//		System.out.println(tlist.toString());
//		System.out.println(tlist.size());
		// 코드
//		System.out.println(gugun);
//		System.out.println(clist.toString());
//		System.out.println(hlist.toString());
		mv.setViewName("house/around_search");
		return mv;

	}
	
	// header에서 주변탐방으로 이동할떄 리스트를 다갖고가서 뿌린다.
	@GetMapping(value="list")
	public ModelAndView mvHouseDeal() {
		ModelAndView mv = new ModelAndView();	
		List<HouseDealDto> tlist = houseDealService.getall();
		mv.addObject("firstlist",tlist);
		mv.setViewName("house/around_search");
		return mv;
		
	}
	// 아파트 이름으로 검색하기
	@GetMapping("api/ {}")
	@ResponseBody
	public 	List<HouseDealDto> searchapt (@PathVariable String aptname) {
		List<HouseDealDto> tlist = houseDealService.getall();
		for (HouseDealDto now : tlist) {
			if(now.getAptname().contains(aptname)) {
				
			}
		}
		return null;
	}
	
}

