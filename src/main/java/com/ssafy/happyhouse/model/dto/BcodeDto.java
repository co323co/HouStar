package com.ssafy.happyhouse.model.dto;

public class BcodeDto {
	private String bcode;
	private String bname;
	private String existence;
	
	
	public String getBcode() {
		return bcode;
	}
	public void setBcode(String bcode) {
		this.bcode = bcode;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getExistence() {
		return existence;
	}
	public void setExistence(String existence) {
		this.existence = existence;
	}
	
	public BcodeDto(String bcode, String bname) {
		super();
		this.bcode = bcode;
		this.bname = bname;
	}
	
	
	
	
}
