package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunDongDto;

public interface SidoGugunDongService {
	
	List<SidoGugunDongDto> getSido() throws Exception;
	List<SidoGugunDongDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugunhasDeal(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<SidoGugunDongDto> getDongByGugun(String guguncode)throws Exception;
	SidoGugunDongDto getDong(String dongcode);
}
