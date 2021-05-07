package com.ssafy.model.service;

import java.util.List;

import com.ssafy.model.Notice;

public interface NoticeService {

	public List<Notice> searchAll();
	public Notice searchById(int id);
	public List<Notice> searchByTitle(String str);
	public boolean insertNotice(Notice notice);
	public boolean deleteNoticeById(int id);
	public boolean updateNotice(Notice notice);
	
}
