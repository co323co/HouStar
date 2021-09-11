package com.ssafy.happyhouse.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BoardDto : 게시글 정보", description = "게시글 id, 제목, 내용, 작성자id, 작성시간, 조회수, 게시판 구분을 담고있습니다")
public class BoardDto {

	@ApiModelProperty("게시글 id")
	int id;
	@ApiModelProperty("게시글 제목")
	String title;
	@ApiModelProperty("게시글 내용")
	String content;
	@ApiModelProperty("작성자 id")
	String userid;
	@ApiModelProperty("게시글 작성 시간")
	String regtime;
	@ApiModelProperty("조회수")
	int views;
	@ApiModelProperty("게시판 구분(gubun Integer(pk))")
	int gubun;
	
	public BoardDto() {}

	public BoardDto(int id, String title, String content, String userid, String regtime, int views, int gubun) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.userid = userid;
		this.regtime = regtime;
		this.views = views;
		this.gubun = gubun;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getGubun() {
		return gubun;
	}

	public void setGubun(int gubun) {
		this.gubun = gubun;
	}

	
}
