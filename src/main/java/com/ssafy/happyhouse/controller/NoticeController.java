package com.ssafy.happyhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@GetMapping
	public String notice() {
		return "/board/notice";
	}

//	@GetMapping("notice_update")
//	public String noticeUpdate() {
//		return "/board/notice_update";
//	}

	//notice_submit.jsp로 이동
	@GetMapping("writeForm")
	public String mvWriteForm() {
		return "/board/notice_submit";
	}

	@PostMapping
	public String write() {
		return "/board/notice_submit";
	}
	
}
