package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.service.MemberService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private MemberService memberService;

	//관리자 페이지
	//회원전체검색
	@GetMapping(value = "/user")
	public ResponseEntity<List<MemberDto>> userList() {
		logger.debug("userList");
		List<MemberDto> list = memberService.userList();
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	//등록
	@PostMapping(value = "/user")
	public ResponseEntity<List<MemberDto>> userRegister(@RequestBody MemberDto memberDto) {	
			memberService.insertMember(memberDto);
			List<MemberDto> list = memberService.userList();
			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
	}
	//상세검색
	@GetMapping(value = "/user/{userId}")
	public ResponseEntity<MemberDto> userInfo(@PathVariable("userId") String userId) {
		logger.debug("파라미터 : {}", userId);
		MemberDto memberDto = memberService.lookupmember(userId);
		if(memberDto != null)
			return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/user/{word}")
	public ResponseEntity<MemberDto> search(@PathVariable("word") String word) {
		
	

	}
	//수정
	@PutMapping(value = "/user")
	public ResponseEntity<List<MemberDto>> userModify(@RequestBody MemberDto memberDto) {
		memberService.update(memberDto);
		List<MemberDto> list = memberService.userList();
		return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
	}
	//삭제
	@DeleteMapping(value = "/user/{userId}")
	public ResponseEntity<List<MemberDto>> userDelete(@PathVariable("userId") String userId) {
		memberService.deleteMember(userId);
		List<MemberDto> list = memberService.userList();
		return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
	}
	 
	
	
}
