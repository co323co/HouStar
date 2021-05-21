package com.ssafy.happyhouse.controller;

import java.util.List;

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
	
	@ApiOperation("userid 값을 가진 dongreview Dto를 반환한다.")
	@GetMapping("/{userid}")
	public DongReviewDto getDongReview(@PathVariable String userid) {
		return dSer.getDongReview(userid);
	}
	@ApiOperation("dongcode에 해당하는 dongreview들을 모두 반환한다.")
	@GetMapping("/list/{dongcode}")
	public List<DongReviewDto> getDongReviewList(@PathVariable String dongcode){
		return dSer.getDongReviewList(dongcode);
	}
	@ApiOperation("dongreview를 새로 등록한다.")
	@PostMapping("")
	public boolean insert(@RequestBody DongReviewDto dongReviewDto) {
		return dSer.insert(dongReviewDto);
	}
	@ApiOperation("dongcode에 해당하는 dongreview를 업데이트한다.")
	@PutMapping("")
	public boolean update(@RequestBody DongReviewDto dongReviewDto) {
		return dSer.update(dongReviewDto);
	}
	@ApiOperation("userid에 해당하는 dongreview를 삭제한다.")
	@DeleteMapping("/{userid}")
	public boolean delete(@PathVariable String userid) {
		return dSer.delete(userid);		
	}
	// 그냥 여기서 다 가져와서 % 6 해서 리턴
	@GetMapping("totalavg/{dongcode}")
	public Double getTotalAvg(@PathVariable String dongcode) {
		double TotalAvg=(dSer.getInfraAvg(dongcode) + dSer.getTransAvg(dongcode)
		+ dSer.getSaftyAvg(dongcode) + dSer.getHealthAvg(dongcode)
		+ dSer.getSchoolAvg(dongcode)+ dSer.getEnvironmentAvg(dongcode)) / 6;
		System.out.println(TotalAvg);
		TotalAvg = Math.round(TotalAvg * 100) / 100.0;
		return TotalAvg;		
	}
	@GetMapping("/infra/{dongcode}")
	public Double getInfraAvg(@PathVariable String dongcode) {
		return dSer.getInfraAvg(dongcode);
	}
	@GetMapping("/trans/{dongcode}")
	public Double getTransAvg(@PathVariable String dongcode) {
		return dSer.getTransAvg(dongcode);
	}
	@GetMapping("/safety/{dongcode}")
	public Double getSaftyAvg(@PathVariable String dongcode) {
		return dSer.getSaftyAvg(dongcode);
	}
	@GetMapping("/health/{dongcode}")
	public Double getHealthAvg(@PathVariable String dongcode) {
		return dSer.getHealthAvg(dongcode);
	}
	@GetMapping("/school/{dongcode}")
	public Double getSchoolAvg(@PathVariable String dongcode) {
		return dSer.getSchoolAvg(dongcode);
	}
	@GetMapping("/environment/{dongcode}")
	public Double getEnvironmentAvg(@PathVariable String dongcode) {
		return dSer.getEnvironmentAvg(dongcode);
	}

}
