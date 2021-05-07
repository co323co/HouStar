package com.ssafy.model.service;

import java.util.List;

import com.ssafy.model.InterestArea;
import com.ssafy.model.dao.InterestAreaDAO;
import com.ssafy.model.dao.InterestAreaDAOlmpl;

public class InterestAreaServicelmpl implements InterestAreaService {
	InterestAreaDAO dao;
	
	public InterestAreaServicelmpl() {
		dao =  new InterestAreaDAOlmpl();
	}
	
	
	@Override
	public void insertArea(InterestArea interestArea) {
		dao.insertArea(interestArea);

	}

	@Override
	public void deleteArea(String code, String userId) {
		dao.deleteArea(code,userId);

	}

	@Override
	public List<String> findArea(String userid) {
		// TODO Auto-generated method stub
		return dao.findArea(userid);
	}

}
