package com.ssafy.happyhouse.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "StoreDto : 상가 정보", description = "상가의 정보를 담고 있습니다.")
public class StoreDto {
	@ApiModelProperty("상호명")
	private String name;		//상호명 
	@ApiModelProperty("상권업종대분류명")
	private String type;		//상권업종대분류명 
	@ApiModelProperty("법정동코드")
	private String dongcode; 	//법정동코드
	@ApiModelProperty("법정동명")
	private String dongname;	//법정동명 
	@ApiModelProperty("도로명주소")
	private String address;		//도로명주소 
	@ApiModelProperty("위도")
	private String lat;			//위도 
	@ApiModelProperty("경도")
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
