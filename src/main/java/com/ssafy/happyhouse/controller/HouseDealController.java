package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	ModelAndView mvHouseDeal(@PathVariable("dong") String dong, ModelAndView mv) {
		List<HouseDealDto> tlist = houseDealService.treadInfo(dong);
		mv.addObject("tlist",tlist);
		mv.addObject("dong",dong);
		System.out.println(tlist.toString());
		System.out.println(tlist.size());
		mv.setViewName("house/around_search2");
		return mv;

	}
	
	
}
