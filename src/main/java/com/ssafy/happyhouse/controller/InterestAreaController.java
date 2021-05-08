package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("/interest/searchPage")
	String mvSearchPage() {
		return "/interest/inter_search";
	}
	
	@PostMapping("/api/interest")
	@ResponseBody
	int insert(@RequestBody String code, HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", code);
		map.put("userId", (String) session.getAttribute("userId"));
		return iSer.insert(map);
	}
	
	@DeleteMapping("/api/interest")
	@ResponseBody
	int delete(@RequestBody String code, HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", code);
		map.put("userId", (String) session.getAttribute("userId"));
		return iSer.delete(map);
	}
	
	//해당 유저의 관심지역 리스트를 반환함
	@GetMapping("/api/interest/list")
	@ResponseBody
	List<InterestAreaDto> list(HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		return iSer.list(userId);
	}
	
	//sido, gugun, dong 이름을 받아서 code로 반환
	@GetMapping("/api/interest/covert")
	@ResponseBody
	Map<String, String> addressToCode(@RequestParam Map<String, String> map) {
		Map<String, String> rs = new HashMap<String, String>();
		rs.put("code", iSer.addressToCode(map));
		return rs;
	}
	
}
