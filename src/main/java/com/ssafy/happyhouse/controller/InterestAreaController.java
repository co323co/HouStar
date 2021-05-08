package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.happyhouse.model.dto.InterestAreaDto;
import com.ssafy.happyhouse.model.service.InterestAreaService;

@Controller
public class InterestAreaController {

	@Autowired
	InterestAreaService iSer;
	
	@GetMapping("/interest/registform")
	String mvRegistForm() {
		return "/interest/inter_regist";
	}
	
	@PostMapping("/api/interest")
	@ResponseBody
	int insert(InterestAreaDto interestArea) {
		return iSer.insert(interestArea);
	}
	
	@DeleteMapping("/api/interest")
	@ResponseBody
	int delete(@RequestParam String code, @RequestParam String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", code);
		map.put("userId", userId);
		return iSer.delete(map);
	}

	//해당 유저의 관심지역 리스트를 반환함
	@GetMapping("/api/interest/list/{userId}")
	@ResponseBody
	List<InterestAreaDto> list(@PathVariable String userId) {
		return iSer.list(userId);
	}
	
}
