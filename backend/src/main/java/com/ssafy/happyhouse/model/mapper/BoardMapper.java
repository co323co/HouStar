package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.BoardDto;

public interface BoardMapper {

	public List<BoardDto> listByBid(Map<String, String> map);
	public BoardDto select(int id);
	public int insert(BoardDto board);
	public int update(BoardDto board);
	public int delete(int id);
	public String findNameByGubun(int gubun);
	
	public List<BoardDto> getBoardsByView();
	public List<Map<String, Object>> getCommunityRanking();
	public List<Map<String, Object>> selectAllCommunity();
	public boolean insertCommunity(String name);
	public boolean updateCommunity(Map<String, Object> map);
	public boolean deleteCommunity(int gubun);
}
