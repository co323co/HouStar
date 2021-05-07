package com.ssafy.happyhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("house")
public class HouseController {

	@GetMapping("/deal")
	String mvHouseDeal() {
		return "house/around_search";
	}
	
}
