package com.ssafy.happyhouse.controller;

import java.util.ArrayList;
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
import com.ssafy.happyhouse.model.dto.MartDto;
import com.ssafy.happyhouse.model.service.InterestAreaService;
import com.ssafy.happyhouse.model.service.MartService;

@Controller
public class InterestAreaController {

	@Autowired
	InterestAreaService iSer;
	@Autowired
	MartService martService;
	
	@GetMapping("/interest/registform")
	String mvRegistForm() {
		return "/interest/inter_regist";
	}

	@GetMapping("/interest/searchPage")
	String mvSearchPage() {
		return "/interest/inter_search";
	}

	@GetMapping("/interest/envInfoPage")
	String mvEnvInfoPage() {
		return "/interest/inter_envinfo";
	}
	
	@PostMapping("/api/interest")
	@ResponseBody
	int insert(@RequestBody String code, HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", code);
		map.put("userid", (String) session.getAttribute("userid"));
		return iSer.insert(map);
	}
	
	@DeleteMapping("/api/interest")
	@ResponseBody
	int delete(@RequestBody String code, HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", code);
		map.put("userid", (String) session.getAttribute("userid"));
		return iSer.delete(map);
	}
	
	//해당 유저의 관심지역 리스트를 반환함
	@GetMapping("/api/interest/list")
	@ResponseBody
	List<InterestAreaDto> list(HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		List<InterestAreaDto> list = iSer.list(userid);
		System.out.println(list.toString());
		return list;
	}
	
	//sido, gugun, dong 이름을 받아서 code로 반환
	@GetMapping("/api/interest/covert")
	@ResponseBody
	Map<String, String> addressToCode(@RequestParam Map<String, String> map) {
		Map<String, String> rs = new HashMap<String, String>();
		rs.put("code", iSer.addressToCode(map));
		return rs;
	}
	
	// id로 관심지역의 법정동코드 반환
	// 법정동코드로 mart list 를 받아서 화면에 출력 
	@GetMapping("/api/interest/mart")
	@ResponseBody
	public List<MartDto> mlist (HttpSession session){
		String userid = (String) session.getAttribute("userid");
		List<InterestAreaDto> list =iSer.list(userid);
		String code = list.get(0).getCode();
		List<MartDto> martlist = martService.getinfo(code);
		return martlist;
	}
	// 상호업종 대분류명 가꼬와서 리스트새로만들어서뿌려
	@GetMapping("/api/interest/mart/{city}")
	@ResponseBody
	public List<MartDto> newmartlist (@PathVariable("city") String city, HttpSession session){
		if(city.contains("temp")) {
			city = city.replaceAll("temp", "/");
		}
		System.out.println(city);
		String userid = (String) session.getAttribute("userid");	
		List<InterestAreaDto> list =iSer.list(userid);
		String code = list.get(0).getCode();
		List<MartDto> martlist = martService.getinfo(code);
		if(city.equals("all")) {
			return martlist;
		}
		List<MartDto> newmartlist = new ArrayList<>();
		for (MartDto m : martlist) {
			if(m.getBictitle().equals(city)) {
				newmartlist.add(m);
				System.out.println("담아써 새거에" + m.toString());
			}
		}
		
		return newmartlist;
	}
}
