package com.ssafy.happyhouse.controller;


import java.util.HashMap;
import java.util.Map;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("회원 관리 컨트롤러 API")
@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class MemberController {
	
	@Autowired
	private MemberService mSer;

	@ApiOperation("map에 ( 'id', 'password' ) 필요, 일치하는 유저가 있는지 검사해 boolean을 반환한다")
	@PostMapping("/login")
	public boolean login(@RequestBody Map<String, String> map) {
		System.out.println(map);
		System.out.println(map.get("id") +","+map.get("password"));
		return mSer.login(map);
	}

	@ApiOperation("회원가입")
	@PostMapping("")
	public boolean regist(@RequestBody MemberDto member) {
		System.out.println(member);
		return mSer.insert(member);
	}
	
	@ApiOperation("userid로 유저 정보를 찾아서 반환한다")
	@GetMapping("/{userid}")
	public MemberDto userInfo(@PathVariable String userid) {
		System.out.println(userid);
		System.out.println( mSer.select(userid));
		return mSer.select(userid);
	}
	
	
	@GetMapping("/findpassword/{id}/{name}/{phone}")
	@ResponseBody
	public Map<String, String> findPassWord(@PathVariable String id, @PathVariable String name, @PathVariable String phone) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("name", name);
		map.put("phone", phone);
		return mSer.findPassword(map);
	}
	
	
	@PutMapping("/api/user")
	public @ResponseBody ResponseEntity<MemberDto> userModify(@RequestBody MemberDto memberDto) {
		System.out.println(memberDto.toString());
		mSer.update(memberDto);		
		String userid = memberDto.getUserid();		
		memberDto = mSer.select(userid);
		return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
	}
	//회원탈퇴
	@GetMapping(value="/user/delete")
	public String delete(HttpSession session) {
		System.out.println("삭제진행");
		String userid = (String)session.getAttribute("userid");
		mSer.deleteMember(userid);
		session.invalidate();
		return "index";
	}



}
