package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunDongDto;
import com.ssafy.happyhouse.model.mapper.SidoGugunDongMapper;

@Service
public class SidoGugunDongServiceImpl implements SidoGugunDongService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<SidoGugunDongDto> getSido() throws Exception {
		return sqlSession.getMapper(SidoGugunDongMapper.class).getSido();
	}

	@Override
	public List<SidoGugunDongDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(SidoGugunDongMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(SidoGugunDongMapper.class).getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return sqlSession.getMapper(SidoGugunDongMapper.class).getAptInDong(dong);
	}

}
