package com.ssafy.model.service;

import com.ssafy.model.Member;
import com.ssafy.model.dao.MemberDAO;
import com.ssafy.model.dao.MemberDAOImpl;


public class MemberServiceImpl implements MemberService {
	MemberDAO dao;

	public MemberServiceImpl() {
		dao =  new MemberDAOImpl();
	}
	
	
	@Override
	public void insertMember(Member member) {
		System.out.println("MemberServiceImpl" + "insertMember 실행");
		dao.insertMember(member);
	}

	@Override
	public boolean login(String id, String password) {
		// TODO Auto-generated method stub
		return dao.login(id, password);
	}

	@Override
	public void update(Member member) {
		dao.update(member);
		
	}

	@Override
	public void deleteMember(String id) {
		dao.deleteMember(id);
		
	}


	@Override
	public Member lookupmember(String id) {
		
		return dao.lookupmember(id);
	}


	@Override
	public String findPassword(String id, String name, String phoneNumber) {
		return dao.findPassword(id, name, phoneNumber);
	}	
	
	

	
	
	
	
	
	

}
