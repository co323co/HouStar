package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.NoticeDto;

public interface NoticeService {

	public List<NoticeDto> list(Map<String, String> map);
	public NoticeDto search(int id);
	public boolean insert(NoticeDto notice);
	public boolean delete(int id);
	public boolean update(NoticeDto notice);
	
}
