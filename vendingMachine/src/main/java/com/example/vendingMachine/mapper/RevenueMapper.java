package com.example.vendingMachine.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.vendingMachine.dto.Revenue;

@Mapper
public interface RevenueMapper {
	
	// 음료 구매시 수익 추가
	int insertRevenue(Revenue revenue);
	// 수익 리스트 조회
	List<Map<String, Object>> selectRevenues();
}
