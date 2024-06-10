package com.example.vendingMachine.service;

import java.util.List;
import java.util.Map;

public interface RevenueService {

	// 수익 목록 조회
	List<Map<String, Object>> getRevenues();
	// 수익 추가
	void addRevenue(int beverageNo);
}
