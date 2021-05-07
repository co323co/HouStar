package com.ssafy.happyhouse.model.service;

import java.lang.reflect.Member;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.mapper.MemberMapper;


@Service
public class MemberServiceImpl implements MemberService {
	
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean login(Map<String, String> map) {
		if(map.get("userId") == null || map.get("userPwd") == null)
			return false;
		return sqlSession.getMapper(MemberMapper.class).login(map);
	}

	@Override
	public MemberDto lookupmember(String id) {	
		return sqlSession.getMapper(MemberMapper.class).lookupmember(id);
	}

	@Override
	public void update(MemberDto memberDto) {
		sqlSession.getMapper(MemberMapper.class).update(memberDto);
		
	}
	
	
//	@Override
//	public void insertMember(com.ssafy.happyhouse.model.service.Member member) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public void update(com.ssafy.happyhouse.model.service.Member member) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteMember(String id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public com.ssafy.happyhouse.model.service.Member lookupmember(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String findPassword(String id, String name, String phoneNumber) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//

	
	
	
	
	
	

}
