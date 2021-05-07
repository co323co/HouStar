package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.service.HouseDealService;

@Controller
@RequestMapping("housedeal")
public class HouseDealController {

	@Autowired
	HouseDealService houseDealService;
	
	@GetMapping("list/{dong}")
	ModelAndView mvHouseDeal(@RequestParam("dong") HouseDealDto dong, ModelAndView mv) {
		String dong1 = dong.getDong();
		List<HouseDealDto> tlist = houseDealService.treadInfo(dong1);
		mv.addObject("tlist",tlist);
		System.out.println(tlist.toString());
		mv.setViewName("house/around_search");
		return mv;

	}
	
	
}
