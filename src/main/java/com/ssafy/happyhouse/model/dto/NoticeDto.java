package com.ssafy.happyhouse.model.dto;

public class NoticeDto {

	int id;
	String title, content;
	int views;
	
	public NoticeDto(String title, String content) {
		this.title = title;
		this.content = content;
	}
	public NoticeDto(int id, String title, String content, int views) {
		this.id = id;
		this.views = views;
		this.title = title;
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
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
}
