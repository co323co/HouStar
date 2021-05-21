package com.ssafy.happyhouse.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SidoGugunDongDto : 시 군구 동 정보", description = "sidoCode, 시 이름, gugunCode, 구 이름 , dongCode, 동 이름을 담고있습니다"
		+ "		예시 : sidoCode=11, sidoName=서울특별시, gugunCode=11590, gugunName=동작구, dongCode=1159010200, dongName=상도동")
public class SidoGugunDongDto {
	
	//시도 코드 (앞 두글자) ex) 서울 : 11
	@ApiModelProperty("sidoCode")
	private String sidoCode;
	@ApiModelProperty("시 이름")
	private String sidoName;
	@ApiModelProperty("gugunCode")
	private String gugunCode;
	@ApiModelProperty("구 이름")
	private String gugunName;
	@ApiModelProperty("dongCode")
	private String dongCode;
	@ApiModelProperty("동 이름")
	private String dongName;
	
	public String getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunCode() {
		return gugunCode;
	}
	public void setGugunCode(String gugunCode) {
		this.gugunCode = gugunCode;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	@Override
	public String toString() {
		return "SidoGugunDongDto [sidoCode=" + sidoCode + ", sidoName=" + sidoName + ", gugunCode=" + gugunCode
				+ ", gugunName=" + gugunName + ", dongCode=" + dongCode + ", dongName=" + dongName + "]";
	}
	
	
}
