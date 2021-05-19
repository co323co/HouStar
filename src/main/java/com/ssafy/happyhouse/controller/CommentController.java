package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.dto.CommentDto;
import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.service.BoardService;
import com.ssafy.happyhouse.model.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("댓글(Comment) API")
@RestController
@CrossOrigin("*")
@RequestMapping("api/comment")
public class CommentController {

	@Autowired
	CommentService cSer;

	@ApiOperation("id가 bid인 게시글의 모든 댓글을 반환한다.")
	@GetMapping("/{bid}")
	@ResponseBody
	public List<CommentDto> listByBId(@PathVariable int bid) {
		return cSer.listByBid(bid);
	}
	
	@ApiOperation("게시글 id가 bid인 게시글에서의 seq번째 댓글 정보를 반환한다")
	@GetMapping("/{bid}/{seq}")
	public CommentDto view(@PathVariable int bid, @PathVariable int seq) {
		Map<String, Integer> map = new HashMap<>();
		map.put("bid", bid);
		map.put("seq", seq);
		CommentDto comment = cSer.select(map);
		return comment;
	}

	
	@ApiOperation("bid, content, userid가 들어간 comment 정보로 새로운 댓글을 등록한다 (나머지 칼럼은 자동 등록)")
	@PostMapping("")
	public boolean write(@RequestBody CommentDto comment) {
		System.out.println(comment);
		return cSer.insert(comment);
	}
	
	@ApiOperation("comment의 bid와 seq가 일치하는 댓글의 내용(content)을 수정한다.(나머지는 수정되지 않음)")
	@PutMapping("")
	public boolean modify(@RequestBody CommentDto comment) {
		return cSer.update(comment);
	}
	
	@ApiOperation("id가 bid인 게시글의 seq번째 댓글을 삭제한다")
	@DeleteMapping("/{bid}/{seq}")
	public boolean remove(@PathVariable int bid, @PathVariable int seq) {
		Map<String, Integer> map = new HashMap<>();
		map.put("bid", bid);
		map.put("seq", seq);
		return cSer.delete(map);
	}
	
}
