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
import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("게시판, 게시글 컨트롤러 API")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("api/board")
public class BoardController {

	@Autowired
	BoardService bSer;

	@ApiOperation("게시판 gubun으로 해당 게시판의 모든 게시글을 반환, word가 있을 경우 제목에 word가 포함된 게시글만 반환한다")
	@GetMapping({"/{gubun}", "/{gubun}/{word}"})
	@ResponseBody
	public List<BoardDto> listById(@PathVariable String gubun, @PathVariable(required=false) String word) {
		Map<String, String> map = new HashMap<>();
		System.out.println(word);
		map.put("gubun", gubun);
		map.put("word", word);
		return bSer.listByBid(map);
	}
	
	@ApiOperation("gubun(pk)으로 해당 게시판의 이름을 반환한다.")
	@GetMapping("name/{gubun}")
	@ResponseBody
	public String findNameByGubun(@PathVariable int gubun) {
		return bSer.findNameByGubun(gubun);
	}

	@ApiOperation("게시글 id로 해당 게시글에 대한 정보를 얻어온다")
	@GetMapping("post/{id}")
	public BoardDto view(@PathVariable("id") int id) {
		BoardDto board = bSer.select(id);
		board.setViews(board.getViews()+1);	//조회수 1 올려줌
		bSer.update(board);
		return board;
	}

	
	@ApiOperation("게시판 gubun으로 새로운 게시글을 등록한다, title, content, gubun 필수 / views 선택")
	@PostMapping("post/{gubun}")
	public boolean write(@RequestBody BoardDto board) {
		return bSer.insert(board);
	}
	
	@ApiOperation("해당 id의 게시글을 board의 내용으로 수정한다 (게시글id, 작성자, 작성시간은 수정안됨)")
	@PutMapping("post/{id}")
	public boolean modify(@RequestBody BoardDto board) {
		return bSer.update(board);
	}
	
	@ApiOperation("해당 id의 게시글을 삭제한다")
	@DeleteMapping("post/{id}")
	public boolean remove(@PathVariable("id") int id) {
		return bSer.delete(id);
	}
	@ApiOperation("인기 게시글 상위 5개를 조회수로 반환한다.")
	@GetMapping("hotpost")
	public List<BoardDto> getBoardsByView(){
		return bSer.getBoardsByView();
	}
	@ApiOperation("인기 커뮤니티 상위 5개를 게시된 글 수의 총합으로 계산하여 반환한다.")
	@GetMapping("hotcommunity")
	public  List<Map<String, Object>> getCommunityRanking(){
		return bSer.getCommunityRanking();
	}
	@ApiOperation("모든 커뮤니티를 반환한다.")
	@GetMapping("community")
	public  List<Map<String, Object>> selectAllCommunity(){
		return bSer.selectAllCommunity();
	}
	@ApiOperation("커뮤니티를 추가한다.")
	@PostMapping("community")
	public boolean insertCommunity (@RequestBody Map<String, Object> map) {
		String name = (String) map.get("name");
		return bSer.insertCommunity(name);	
	}
	@ApiOperation("커뮤니티를 수정한다.")
	@PutMapping("community")
	public boolean updateCommunity (@RequestBody Map<String, Object> map) {
		return bSer.updateCommunity(map);
	}
	@ApiOperation("커뮤니티를 삭제한다.")
	@DeleteMapping("community/{gubun}")
	public boolean deleteCommunity (@PathVariable int gubun) {
		return bSer.deleteCommunity(gubun);
	}
	
}
