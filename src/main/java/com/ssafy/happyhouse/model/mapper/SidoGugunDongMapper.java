package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunDongDto;

public interface SidoGugunDongMapper {

	List<SidoGugunDongDto> getSido() throws Exception;
	List<SidoGugunDongDto> getGugunInSido(String sido) throws Exception;
	//거래 정보가 있는 동만 반환
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	SidoGugunDongDto getByDongCode(String dongCode);

}
