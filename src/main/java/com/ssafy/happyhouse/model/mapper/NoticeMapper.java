package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.NoticeDto;

public interface NoticeMapper {

	public List<NoticeDto> list(Map<String, String> map);
	public NoticeDto search(int id);
	public int insert(NoticeDto notice);
	public int delete(int id);
	public int update(NoticeDto notice);
	
}
