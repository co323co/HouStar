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

	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map, HttpSession session) {
		boolean login = mSer.login(map);
		if(login) {			
			String userid = map.get("login_userid");		
			session.setAttribute("userid", userid);		
			return "index";
		}else {
			return "/user/login";
		}	
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@ApiOperation("회원가입")
	@PostMapping("")
	public boolean regist(@RequestBody MemberDto member) {
//		mSer.insertMember(member);
		System.out.println(member);
		return true;
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
	
	//rest로 Userinfo 입장 시 로그인정보 뿌리기
	@GetMapping(value="/api/user")
	public @ResponseBody ResponseEntity<MemberDto> userInfo(HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		
		MemberDto memberDto = mSer.lookupmember(userid);
		if(memberDto != null)
			return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
		else
			return new ResponseEntity<MemberDto>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/api/user")
	public @ResponseBody ResponseEntity<MemberDto> userModify(@RequestBody MemberDto memberDto) {
		System.out.println(memberDto.toString());
		mSer.update(memberDto);		
		String userid = memberDto.getUserid();		
		memberDto = mSer.lookupmember(userid);
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
