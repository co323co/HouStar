package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.BoardDto;

public interface BoardService {

	public List<BoardDto> listByBid(Map<String, String> map);
	public BoardDto select(int id);
	public boolean insert(BoardDto board);
	public boolean delete(int id);
	public boolean update(BoardDto board);
	public String findNameByGubun(int id);
}
