package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.dto.NoticeDto;

public interface NoticeMapper {

	public List<NoticeDto> list();
	public NoticeDto search(int id);
	public boolean insert(NoticeDto notice);
	public boolean delete(int id);
	public boolean update(NoticeDto notice);
	
}
