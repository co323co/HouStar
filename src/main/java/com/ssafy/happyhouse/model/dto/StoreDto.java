package com.ssafy.happyhouse.model.dto;

public class StoreDto {
	private String name;		//상호명 
	private String type;		//상권업종대분류명 
	private String dongcode; 	//법정동코드
	private String dongname;	//법정동명 
	private String address;		//도로명주소 
	private String lat;			//위도 
	private String lng;			//경도
	
	public StoreDto(String name, String type, String dongcode, String dongname, String address, String lat,
			String lng) {
		super();
		this.name = name;
		this.type = type;
		this.dongcode = dongcode;
		this.dongname = dongname;
		this.address = address;
		this.lat = lat;
		this.lng = lng;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDongcode() {
		return dongcode;
	}
	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}
	public String getDongname() {
		return dongname;
	}
	public void setDongname(String dongname) {
		this.dongname = dongname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
