package com.ssafy.model.service;

import java.util.List;

import com.ssafy.model.Mart;
import com.ssafy.model.dao.MartDAO;
import com.ssafy.model.dao.MartDAOlmpl;
import com.ssafy.model.dao.MemberDAOImpl;

public class MartServicelmpl implements MartService {
	MartDAO dao;
	
	
	
	public MartServicelmpl() {
		dao =  new MartDAOlmpl();
	}
	
	@Override
	public List<Mart> getinfo(String dong) {
		
		return dao.getinfo(dong);
	}

}
