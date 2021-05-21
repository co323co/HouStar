package com.ssafy.happyhouse.model.service;

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
		if(map.get("login_userid") == null || map.get("login_pwd") == null)
			return false;
		else{
//			System.out.println(map.get("login_userid") +" "+map.get("login_pwd"));	
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

	@Override
	public List<MemberDto> searchbyid(String word) {	
		return sqlSession.getMapper(MemberMapper.class).searchbyid(word);
	}

	@Override
	public Map<String, String> findPassword(Map<String, String> map) {
		return sqlSession.getMapper(MemberMapper.class).findPassword(map);
	}

}
