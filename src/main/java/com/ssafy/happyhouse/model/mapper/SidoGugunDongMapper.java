package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunDongDto;

public interface SidoGugunDongMapper {

	List<SidoGugunDongDto> getSido() throws Exception;
	List<SidoGugunDongDto> getGugunInSido(String sido) throws Exception;
	//거래 정보가 있는 동만 반환
	List<HouseInfoDto> getDongInGugunhasDeal(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	SidoGugunDongDto getByDongCode(String dongCode);
	List<SidoGugunDongDto> getDongByGugun(String guguncode)throws Exception;
	SidoGugunDongDto getDong(String dongcode);
}
