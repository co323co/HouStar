package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import com.ssafy.happyhouse.model.dto.StoreDto;

public interface StoreMapper {

	public List<StoreDto> getinfo(String dongcode);
}
