package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.model.Notice;
import com.ssafy.model.dao.NoticeDaoImpl;

public class NoticeServiceImpl_ implements NoticeService {

	private static NoticeService noticeService;
	
	private NoticeServiceImpl_() {}
	
	public static NoticeService getNoticeService()
	{
		if(noticeService == null)
			noticeService = new NoticeServiceImpl_();
		return noticeService;
	}
	
	
	@Override
	public List<Notice> searchAll() {
		return NoticeDaoImpl.getNoticeDao().searchAll();
	}

	@Override
	public boolean insertNotice(Notice notice) {
		return NoticeDaoImpl.getNoticeDao().insertNotice(notice);
	}

	@Override
	public boolean deleteNoticeById(int id) {
		return NoticeDaoImpl.getNoticeDao().deleteNoticeById(id);
	}

	@Override
	public boolean updateNotice(Notice notice) {
		return NoticeDaoImpl.getNoticeDao().updateNotice(notice);
	}

	@Override
	public Notice searchById(int id) {
		return NoticeDaoImpl.getNoticeDao().searchById(id);
	}

	@Override
	public List<Notice> searchByTitle(String str) {
		return NoticeDaoImpl.getNoticeDao().searchByTitle(str);
	}

}
