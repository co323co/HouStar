package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.EnvInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunDongDto;
import com.ssafy.happyhouse.model.mapper.EnvGuideCheckMapper;
import com.ssafy.happyhouse.model.mapper.SidoGugunDongMapper;

@Service
public class EnvGuideCheckServiceImpl implements EnvGuideCheckService{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<EnvInfoDto> list() {
		return sqlSession.getMapper(EnvGuideCheckMapper.class).list();
	}

	@Override
	public List<EnvInfoDto> listByDong(String dongCode) {
		/*
		 * dongCode로 envinfo_view 정보를 찾아야 하는데
		 * envinfo_view에는 dongCode가 없고 주소밖에 없음
		 * 그래서 dongCode로 해당 동의 정보를 찾아  %OO시 OO구 OO동% 형태의 문자열을 만들고 그걸로 envinfo_view에서 검색하는 것
		 */
		SidoGugunDongDto sgd = (SidoGugunDongDto) sqlSession.getMapper(SidoGugunDongMapper.class).getByDongCode(dongCode);
		String address = "%" + sgd.getSidoName() +" " + sgd.getDongName() + " " +sgd.getDongName() +"%";	// %OO시 OO구 OO동%		ex) %서울특별시 종로구 관수동%
		return sqlSession.getMapper(EnvGuideCheckMapper.class).listByAddress(address);
	}

}
