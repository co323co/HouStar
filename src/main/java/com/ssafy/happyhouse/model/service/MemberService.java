package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.MemberDto;



public interface MemberService {
	
	public boolean insert(MemberDto memberDto);
	public MemberDto select(String id);
	public boolean login(Map<String, String> map);
	public void update(MemberDto memberDto);
	public void deleteMember(String id);
	public List<MemberDto> userList();
	public List<MemberDto> searchbyid(String word);
	public Map<String, String> findPassword(Map<String, String> map);
	
}
