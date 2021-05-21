package com.ssafy.happyhouse.model.dto;

public class MemberDto {
	private String userid;
	private String pwd;
	private String name;
	private String address;
	private String phone;
	private String tag;
	private int age_range;
	private String family_type;

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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getAge_range() {
		return age_range;
	}
	public void setAge_range(int age_range) {
		this.age_range = age_range;
	}
	public String getFamily_type() {
		return family_type;
	}
	public void setFamily_type(String family_type) {
		this.family_type = family_type;
	}
	@Override
	public String toString() {
		return "MemberDto [userid=" + userid + ", pwd=" + pwd + ", name=" + name + ", address=" + address + ", phone="
				+ phone + ", tag=" + tag + ", age_range=" + age_range + ", family_type=" + family_type + "]";
	}

	
}
