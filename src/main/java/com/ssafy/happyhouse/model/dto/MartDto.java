package com.ssafy.happyhouse.model.dto;

public class MartDto {
	private String martname;	//상호명 b
	private String bictitle;	//상권업종대분류명 e
	private String dong;		//법정동명 s
	private String doro;		//도로명주소 ff
	private String lat;			//경도 ll
	private String lng;			//위도 mm
	
	
	@Override
	public String toString() {
		return "Mart [martname=" + martname + ", bictitle=" + bictitle + ", dong=" + dong + ", doro=" + doro + ", lat="
				+ lat + ", lng=" + lng + "]";
	}
	public MartDto(String martname, String bictitle, String dong, String doro, String lat, String let) {
		this.martname = martname;
		this.bictitle = bictitle;
		this.dong = dong;
		this.doro = doro;
		this.lat = lat;
		this.lng = let;
	}
	public MartDto() {
		// TODO Auto-generated constructor stub
	}
	public String getMartname() {
		return martname;
	}
	public void setMartname(String martname) {
		this.martname = martname;
	}
	public String getBictitle() {
		return bictitle;
	}
	public void setBictitle(String bictitle) {
		this.bictitle = bictitle;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getDoro() {
		return doro;
	}
	public void setDoro(String doro) {
		this.doro = doro;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	
	
	



}
