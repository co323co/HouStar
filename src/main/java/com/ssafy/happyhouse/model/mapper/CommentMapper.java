package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.dto.CommentDto;

public interface CommentMapper {

	public List<CommentDto> listByBid(int bid);
	public CommentDto select(Map<String, Integer> map);
	public int insert(CommentDto comment);
	public int update(CommentDto comment);
	public int delete(Map<String, Integer> map);
	
}
