package com.ssafy.happyhouse.model.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MemberDto : 유저 정보", description = "유저의 정보를 담고 있습니다.")
public class MemberDto {
	@ApiModelProperty("사용자 id")
	private String userid;
	@ApiModelProperty("비밀번호")
	private String pwd;
	@ApiModelProperty("사용자 이름")
	private String name;
	@ApiModelProperty("사용자 주소")
	private String address;
	@ApiModelProperty("전화번호")
	private String phone;
	@ApiModelProperty("선호 태그 목록, ',' 쉼표로 항목을 구분한다.")
	private String tag;
	@ApiModelProperty("가구 형태")
	private String family_type;
	@ApiModelProperty("연령대")
	private Integer age_range;

	public MemberDto() {}

	public MemberDto(String userid, String pwd, String name, String address, String phone, String tag,
			String family_type, Integer age_range) {
		super();
		this.userid = userid;
		this.pwd = pwd;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.tag = tag;
		this.family_type = family_type;
		this.age_range = age_range;
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

	public String getFamily_type() {
		return family_type;
	}

	public void setFamily_type(String family_type) {
		this.family_type = family_type;
	}

	public Integer getAge_range() {
		return age_range;
	}

	public void setAge_range(Integer age_range) {
		this.age_range = age_range;
	}

	@Override
	public String toString() {
		return "MemberDto [userid=" + userid + ", pwd=" + pwd + ", name=" + name + ", address=" + address + ", phone="
				+ phone + ", tag=" + tag + ", family_type=" + family_type + ", age_range=" + age_range + "]";
	}

}
