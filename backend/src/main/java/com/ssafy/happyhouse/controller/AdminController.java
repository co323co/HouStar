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
//
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private MemberService mService;

	//관리자 페이지
	//회원전체검색
	@GetMapping(value = "/user")
	public ResponseEntity<List<MemberDto>> userList() {
		logger.debug("userList");
		List<MemberDto> list = mService.userList();
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	//등록
	@PostMapping(value = "/user")
	public ResponseEntity<List<MemberDto>> userRegister(@RequestBody MemberDto memberDto) {	
			mService.insert(memberDto);
			List<MemberDto> list = mService.userList();
			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
	}
	//상세검색
	@GetMapping(value = "/user/detail/{userId}")
	public ResponseEntity<MemberDto> userInfo(@PathVariable("userId") String userId) {
	
		System.out.println(userId);
		MemberDto memberDto = mService.select(userId);
		if(memberDto != null)
			return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	//아이디로 검색
	@GetMapping(value = "/user/{word}")
	public ResponseEntity<List<MemberDto>> search(@PathVariable("word") String word) {	
		System.out.println(word);
		List<MemberDto> list = mService.searchbyid(word);
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
		}
		else return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	//수정
	@PutMapping(value = "/user")
	public ResponseEntity<List<MemberDto>> userModify(@RequestBody MemberDto memberDto) {
		mService.update(memberDto);
		List<MemberDto> list = mService.userList();
		return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
	}
	//삭제
	@DeleteMapping(value = "/user/{userId}")
	public ResponseEntity<List<MemberDto>> userDelete(@PathVariable("userId") String userId) {
		mService.deleteMember(userId);
		List<MemberDto> list = mService.userList();
		return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
	}
	 
	
	
}
