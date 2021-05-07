package com.ssafy.happyhouse.model.mapper;

import java.util.List;

public interface BcodeDAO {
	public String findDongName (String code);
	public String findCode (String dong);
	//위에건 법정동 코드 디비 쓰기전 방식
	public List<String> findDongNames(List<String> codeList);
	
	public String findBcodeFromBname(String bname);
	public String findBnameFromBcode(String bcode);
	
	
}
