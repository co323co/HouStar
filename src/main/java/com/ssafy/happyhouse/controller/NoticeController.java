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
	
	@GetMapping("/api/list/{word}")
	@ResponseBody
	public List<NoticeDto> listByTitle(@PathVariable("word") String word) {
		Map<String, String> map = new HashMap<>();
		map.put("word", word);
		return nSer.list(map);
	}
	
	@GetMapping("/api/list")
	@ResponseBody
	public List<NoticeDto> list() {
		return nSer.list(new HashMap<>());
	}
	
	//notice_submit.jsp로 이동
	@GetMapping("writeForm")
	public String mvWriteForm() {
		return "/board/notice_submit";
	}
	
	//notice_update.jsp로 이동
	@GetMapping("modifyForm/{id}")
	public ModelAndView mvModifyForm(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("/board/notice_update");
		mv.addObject("notice", nSer.search(id));
		return mv;
	}
	
	@GetMapping("view/{id}")
	public ModelAndView view(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("board/notice_view");
		NoticeDto notice = nSer.search(id);
		notice.setViews(notice.getViews()+1);	//조회수 1 올려줌
		nSer.update(notice);
		mv.addObject("notice", notice);
		return mv;
	}

	
	@PostMapping
	//jsp에서 form action으로 넘겨서 @RequestBody 쓰면 에러남 (나중에 이유 찾기)
	public ModelAndView write(NoticeDto notice) {
		nSer.insert(notice);
		//삽입했으면 notice 게시판으로 다시 이동
		ModelAndView mv = new ModelAndView("redirect:notice/board");
//		mv.addObject("notices", nSer.list(new HashMap<>()));
		return mv;
	}
	
	@PutMapping
	@ResponseBody
	public String modify(@RequestBody NoticeDto notice) {
		nSer.update(notice);
//		System.out.println(notice);
		return "{\"url\" : \"notice/view/" + notice.getId() + "\"}";
	}
	
	@DeleteMapping("{id}")
	@ResponseBody
	public String remove(@PathVariable("id") int id) {
		nSer.delete(id);
		return "{\"url\" : \"notice/board\"}";
	}
	
}
