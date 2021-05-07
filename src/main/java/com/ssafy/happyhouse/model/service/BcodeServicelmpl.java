package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.model.dao.BcodeDAO;
import com.ssafy.model.dao.BcodeDAOlmpl;

public class BcodeServicelmpl implements BcodeService {

	BcodeDAO dao;
	
	public BcodeServicelmpl() {
		dao =  new BcodeDAOlmpl();
	}
	
	
	@Override
	public String findDongName(String code) {

		return dao.findDongName(code);
	}

	@Override
	public String findCode(String dong) {
		
		return dao.findCode(dong);
	}
	
	@Override
	public List<String> findDongNames(List<String> codeList){
		List<String> result = new ArrayList<>();
		for(String code : codeList){
			result.add(findDongName(code));
		}
		return result;
	}


	@Override
	public String findBcodeFromBname(String bname) {
		
		return dao.findBcodeFromBname(bname);
	}


	@Override
	public String findBnameFromBcode(String bcode) {
		
		return dao.findBnameFromBcode(bcode);
	}

}
