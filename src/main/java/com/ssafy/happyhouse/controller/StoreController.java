package com.ssafy.happyhouse.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.StoreDto;
import com.ssafy.happyhouse.model.service.StoretService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("상가  API")
@CrossOrigin("*")
@RestController
@RequestMapping("api/store")
public class StoreController {

   @Autowired
   StoretService sSer;

   @ApiOperation("해당 동의 상가 정보 리스트를 반환한다")
   @GetMapping({"/{dongcode}"})
   public List<StoreDto> getinfo(@PathVariable String dongcode) {
      return sSer.getinfo(dongcode);
   }
   
}
