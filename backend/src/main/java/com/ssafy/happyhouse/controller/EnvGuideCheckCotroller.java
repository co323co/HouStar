package com.ssafy.happyhouse.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.EnvInfoDto;
import com.ssafy.happyhouse.model.service.EnvGuideCheckService;

@RestController
@RequestMapping("/api/envguidecheck")
public class EnvGuideCheckCotroller {
	
	@Autowired
	EnvGuideCheckService eSer;
	
	@GetMapping("list")
	public List<EnvInfoDto> list(){
		return eSer.list();
	}

	@GetMapping("list/{dongCode}")
	public List<EnvInfoDto> listByDong(@PathVariable String dongCode){
		return eSer.listByDong(dongCode);
	}

}
