package com.ssafy.happyhouse.model.service;
import java.util.List;

public interface BcodeService {
	public String findDongName (String code);
	public String findCode (String dong);
	//위는 법정동 디비 쓰기 전 방식
	
	public String findBcodeFromBname(String bname);
	public String findBnameFromBcode(String bcode);
	public List<String> findDongNames(List<String> codeList);
}
