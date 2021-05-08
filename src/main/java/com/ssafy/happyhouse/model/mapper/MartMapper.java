package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import com.ssafy.happyhouse.model.dto.MartDto;

public interface MartMapper {

	public List<MartDto> getinfo(String dong);
}
