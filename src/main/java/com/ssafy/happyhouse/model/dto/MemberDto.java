package com.ssafy.happyhouse.model.dto;

public class MemberDto {
	private String userId;
	private String userPwd;
	private String userName;
	private String userAddress;
	private String userPhone;
	
	public MemberDto() {}
	public MemberDto(String userPwd, String userName, String userAddress, String userPhone) {
		this.userPwd = userPwd;
		this.userName = userName;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
	}


	public MemberDto(String userId, String userPwd, String userName, String userAddress, String userPhone) {
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	@Override
	public String toString() {
		return "MemberDto [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", userAddress="
				+ userAddress + ", userPhone=" + userPhone + "]";
	}
	
	
}
