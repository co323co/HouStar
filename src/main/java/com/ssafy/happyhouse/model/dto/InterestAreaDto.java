package com.ssafy.happyhouse.model.dto;

public class InterestAreaDto {
	
	private String userid;
	private String code;
	private String dong;
	private String go;
	private String si;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getGo() {
		return go;
	}
	public void setGo(String go) {
		this.go = go;
	}
	public String getSi() {
		return si;
	}
	public void setSi(String si) {
		this.si = si;
	}
	public InterestAreaDto(String userid, String dong) {
		super();
		this.userid = userid;
		this.dong = dong;
	}

	

	
	
	
	

}
