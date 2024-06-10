package com.example.vendingMachine.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vendingMachine.dto.Beverage;
import com.example.vendingMachine.dto.Revenue;
import com.example.vendingMachine.mapper.BeverageMapper;
import com.example.vendingMachine.mapper.RevenueMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class RevenueServiceImpl implements RevenueService{

	private final RevenueMapper revenueMapper;
	private final BeverageMapper beverageMapper;
	
	@Override
	public List<Map<String, Object>> getRevenues() {
		return revenueMapper.selectRevenues();
	}

	@Override
	public void addRevenue(int inputBeverageNo) {
		Beverage beverage = beverageMapper.selectBeverage(inputBeverageNo);
		Revenue revenue = new Revenue(beverage.getBeverageNo(), beverage.getBeveragePrice());
		
		// 수익 추가
		int row = revenueMapper.insertRevenue(revenue);
		
		if(row != 1 ) {
			// 수익 추가 실패시(or 에러) 메서드 실행(쿼리 실행) 취소
			throw new RuntimeException();
		}
	}

}
