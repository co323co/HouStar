package com.ssafy.happyhouse.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "StoreViewDto : 상가(인프라) 정보", description = "상호명, 상권업종대분류명 ,법정동명, 도로명주소, 경도, 위도를 담고있습니다")
public class StoreViewDto {
	
	@ApiModelProperty("상호명")
	private String name;
	@ApiModelProperty("상권업종대분류명")
	private String type;
	@ApiModelProperty("법정동명 ex 독산동")
	private String dong;
	@ApiModelProperty("도로명주소  ex 서울특별시 금천구 독산로 272 ")
	private String address;
	@ApiModelProperty("경도")
	private String lat;
	@ApiModelProperty("위도")
	private String lng;
	
	public StoreViewDto() {
		super();
	}
	public StoreViewDto(String name, String type, String dong, String address, String lat, String lng) {
		super();
		this.name = name;
		this.type = type;
		this.dong = dong;
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
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
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
	@Override
	public String toString() {
		return "StoreViewDto [name=" + name + ", type=" + type + ", dong=" + dong + ", address=" + address + ", lat="
				+ lat + ", lng=" + lng + "]";
	}
}
