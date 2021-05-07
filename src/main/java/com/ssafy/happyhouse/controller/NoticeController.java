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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.service.NoticeService;

@Controller
@RequestMapping("notice")
public class NoticeController {

	@Autowired
	NoticeService nSer;
	
	@GetMapping("board")
	public String mvNotice() {
		return "/board/notice";
	}
	
//	@GetMapping("/api/list")
////	@GetMapping("/api/list/{word}")
//	@ResponseBody
////	public List<NoticeDto> list(@PathVariable("word") String word) {
//	public List<NoticeDto> list() {
//		Map<String, String> map = new HashMap<>();
////		map.put("word", word);
//		return nSer.list(map);
////		return nSer.list(new HashMap<>());
//	}
	@GetMapping("/api/list")
//	@GetMapping("/api/list/{word}")
	@ResponseBody
//	public List<NoticeDto> list(@PathVariable("word") String word) {
	public List<NoticeDto> list() {
		Map<String, String> map = new HashMap<>();
//		map.put("word", word);
		return nSer.list(map);
//		return nSer.list(new HashMap<>());
	}
	
	//notice_submit.jsp로 이동
	@GetMapping("writeForm")
	public String mvWriteForm() {
		return "/board/notice_submit";
	}

	@PostMapping
	public ModelAndView write(NoticeDto notice) {
		nSer.insert(notice);
		//삽입했으면 notice 게시판으로 다시 이동
		ModelAndView mv = new ModelAndView("redirect:notice/list");
		mv.addObject("notices", nSer.list(new HashMap<>()));
		return mv;
	}
	
	@PutMapping
	public String modify(NoticeDto notice) {
		nSer.update(notice);
		return "/board/notice";
	}
	
	@DeleteMapping
	public String remove(int id) {
		nSer.delete(id);
		return "/board/notice";
	}
	
}
