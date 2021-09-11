package com.ssafy.happyhouse.model.dto;

public class CoronaDto {
	private String cname;
	private String gungu;
	private String address;
	private String otime;
	private String stime;
	private String htime;
	private String phone;
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getGungu() {
		return gungu;
	}
	public void setGungu(String gungu) {
		this.gungu = gungu;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOtime() {
		return otime;
	}
	public void setOtime(String otime) {
		this.otime = otime;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getHtime() {
		return htime;
	}
	public void setHtime(String htime) {
		this.htime = htime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "CoronaDto [cname=" + cname + ", gungu=" + gungu + ", address=" + address + ", otime=" + otime
				+ ", stime=" + stime + ", htime=" + htime + ", phone=" + phone + "]";
	}

	
}
