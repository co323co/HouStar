package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.dto.EnvInfoDto;

public interface EnvGuideCheckDAO {
	
	public List<EnvInfoDto> list();
	/** 해당 동(주소 코드)에 있는 환경정보만 반환함 **/
	public List<EnvInfoDto> listByDong(String code);

}
