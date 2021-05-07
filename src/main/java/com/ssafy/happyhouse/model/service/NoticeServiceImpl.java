package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<NoticeDto> list(Map<String, String> map) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("key", map.get("key") == null ? "" : map.get("key"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int currentPage = Integer.parseInt(map.get("pg"));
//		int sizePerPage = Integer.parseInt(map.get("spp"));
//		int start = (currentPage - 1) * sizePerPage;
//		param.put("start", start);
//		param.put("spp", sizePerPage);
		return sqlSession.getMapper(NoticeMapper.class).list(param);
	}

	@Override
	public NoticeDto search(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(NoticeDto notice) {
		if(sqlSession.getMapper(NoticeMapper.class).insert(notice)!=0) return true;
		return false;
	}

	@Override
	public boolean delete(int id) {
		if(sqlSession.getMapper(NoticeMapper.class).delete(id)!=0) return true;
		return false;
	}

	@Override
	public boolean update(NoticeDto notice) {
		if(sqlSession.getMapper(NoticeMapper.class).update(notice)!=0) return true;
		return false;
	}


}
