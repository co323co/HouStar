package com.ssafy.happyhouse.model.dto;

public class EnvInfoDto {
	
	String code, name, type, address;

	public EnvInfoDto(String code, String name, String type, String address) {
		this.code = code;
		this.name = name;
		this.type = type;
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "EnvInfoDto [code=" + code + ", name=" + name + ", type=" + type + ", address=" + address + "]";
	}
	
}
