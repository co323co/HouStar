package com.ssafy.happyhouse.model.service;

import com.ssafy.model.Member;


public interface MemberService {
	public void insertMember(Member member);
	public boolean login(String id, String password);
	public void update(Member member);
	public void deleteMember(String id);
	public Member lookupmember(String id);
	public String findPassword(String id, String name, String phoneNumber);
	
}
