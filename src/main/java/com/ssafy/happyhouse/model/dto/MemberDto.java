package com.ssafy.happyhouse.model.dto;

public class MemberDto {
	private String userid;
	private String pwd;
	private String name;
	private String address;
	private String phone;
	
	public MemberDto() {}
	public MemberDto(String pwd, String name, String address, String phone) {
		this.pwd = pwd;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}


	public MemberDto(String userid, String pwd, String name, String address, String phone) {
		this.userid = userid;
		this.pwd = pwd;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	
	
	public String getUserId() {
		return userid;
	}
	public void setUserId(String userid) {
		this.userid = userid;
	}
	public String getUserPwd() {
		return pwd;
	}
	public void setUserPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUserName() {
		return name;
	}
	public void setUserName(String name) {
		this.name = name;
	}
	public String getUserAddress() {
		return address;
	}
	public void setUserAddress(String address) {
		this.address = address;
	}
	public String getUserPhone() {
		return phone;
	}
	public void setUserPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "MemberDto [userid=" + userid + ", pwd=" + pwd + ", name=" + name + ", address="
				+ address + ", phone=" + phone + "]";
	}
	
	
}
