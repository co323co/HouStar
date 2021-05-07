package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.MemberDto;



public interface MemberMapper {
	
	public boolean login(Map<String, String> map);
	
	public void insertMember(MemberDto memberDto);
	public void update(MemberDto memberDto);
	public void deleteMember(String id);
	public MemberDto lookupmember(String id);
	public List<MemberDto> userList();
	public List<MemberDto> searchbyid(String word);
	public Map<String, String> findPassword(Map<String, String> map);
	
}
