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
	
	// 커뮤니티 관련 
	public List<BoardDto> getBoardsByView();
	public List<Map<String, Object>> getCommunityRanking();
	public List<Map<String, Object>> selectAllCommunity();
	public boolean insertCommunity(String name);
	public boolean updateCommunity(Map<String, Object> map);
	public boolean deleteCommunity(int gubun);
}
