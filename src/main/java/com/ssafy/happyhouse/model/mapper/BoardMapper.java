package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.BoardDto;

public interface BoardMapper {

	public List<BoardDto> listByBid(Map<String, String> map);
	public BoardDto select(int id);
	public int insert(BoardDto board);
	public int delete(int id);
	public int update(BoardDto board);
	public String findNameByGubun(int gubun);
	
}
