package com.ssafy.happyhouse.model.service;

import java.lang.reflect.Member;
import java.util.List;
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
		else{
//			System.out.println(map.get("userId") +" "+map.get("userPwd"));	
			return sqlSession.getMapper(MemberMapper.class).login(map);
		}
	}

	@Override
	public MemberDto lookupmember(String id) {	
		return sqlSession.getMapper(MemberMapper.class).lookupmember(id);
	}

	@Override
	public void update(MemberDto memberDto) {
		sqlSession.getMapper(MemberMapper.class).update(memberDto);
		
	}

	@Override
	public void deleteMember(String id) {
		sqlSession.getMapper(MemberMapper.class).deleteMember(id);
		
	}

	@Override
	public void insertMember(MemberDto memberDto) {
		sqlSession.getMapper(MemberMapper.class).insertMember(memberDto);
		
	}

	@Override
	public List<MemberDto> userList() {
		return sqlSession.getMapper(MemberMapper.class).userList();
	}


}
