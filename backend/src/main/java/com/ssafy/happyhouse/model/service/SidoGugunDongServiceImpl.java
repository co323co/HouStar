package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

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
	public List<HouseInfoDto> getDongInGugunhasDeal(String gugun) throws Exception {
		return sqlSession.getMapper(SidoGugunDongMapper.class).getDongInGugunhasDeal(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return sqlSession.getMapper(SidoGugunDongMapper.class).getAptInDong(dong);
	}

	@Override
	public List<SidoGugunDongDto> getDongByGugun(String guguncode) throws Exception {
		return  sqlSession.getMapper(SidoGugunDongMapper.class).getDongByGugun(guguncode);
	}

	@Override
	public SidoGugunDongDto getDong(String dongcode) {
		return  sqlSession.getMapper(SidoGugunDongMapper.class).getDong(dongcode);
	}

	@Override
	public Map<String, String> getDongPos(String dongcode) {
		return  sqlSession.getMapper(SidoGugunDongMapper.class).getDongPos(dongcode);
	}
}
