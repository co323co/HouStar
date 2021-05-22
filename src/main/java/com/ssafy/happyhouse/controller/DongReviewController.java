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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.DongReviewDto;
import com.ssafy.happyhouse.model.service.DongReviewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api("DongReview API")
@RestController
@CrossOrigin("*")
@RequestMapping("api/dongreview")
public class DongReviewController {

	@Autowired
	DongReviewService dSer;
	
	@ApiOperation("해당 유저가 쓴 모든 리뷰 가져온다.")
	@GetMapping("/user/{userid}")
	public List<DongReviewDto> getReviewByUser(@PathVariable String userid) {
		Map<String, String> map = new HashMap<String,String>();
		return dSer.getReviewByUser(userid);
	}
	
	@ApiOperation("해당 동의 모든 리뷰 가져온다.")
	@GetMapping("/dong/{dongcode}")
	public List<DongReviewDto> getReviewByDong(@PathVariable String dongcode){
		return dSer.getReviewByDong(dongcode);
	}
	
	@ApiOperation("랭킹용 : 모든 동의 별점 정보를 가져온다")
	@GetMapping("/avg-rating")
	public List<Map<String,String>> getRatingAll(){
		return dSer.getRatingAll();
	}
	
	@ApiOperation("랭킹용 : 해당 동의 평균 별점 정보를 가져온다")
	@GetMapping("/avg-rating/{dongcode}")
	public Map<String,String> getRatingByDong(@PathVariable String dongcode){
		return dSer.getRatingByDong(dongcode);
	}
	
	@ApiOperation("dongreview를 새로 등록한다.")
	@PostMapping("")
	public boolean insert(@RequestBody DongReviewDto dongReviewDto) {
		return dSer.insert(dongReviewDto);
	}
	@ApiOperation("dongcode가 일치하는 dongreview를 업데이트한다.")
	@PutMapping("")
	public boolean update(@RequestBody DongReviewDto dongReviewDto) {
		return dSer.update(dongReviewDto);
	}
	@ApiOperation("userid에 해당하는 dongreview를 삭제한다.")
	@DeleteMapping("/{userid}")
	public boolean delete(@PathVariable String userid) {
		return dSer.delete(userid);		
	}
	
}
