package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;
import com.ssafy.happyhouse.model.dto.CommentDto;

public interface CommentService {

	public List<CommentDto> listByBid(int bid);
	public CommentDto select(Map<String, Integer> map);
	public boolean insert(CommentDto comment);
	public boolean delete(Map<String, Integer> map);
	public boolean update(CommentDto comment);
	
}
