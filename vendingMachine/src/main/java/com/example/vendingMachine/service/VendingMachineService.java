package com.example.vendingMachine.service;

import java.util.List;

import com.example.vendingMachine.dto.Beverage;

public interface VendingMachineService {
	
	// 음료 추가
	boolean addBeverage(Beverage beverage);
	// 음료 재고 수정
	void editBeverageStock(int beverageNo, int newBeverageStock);
	// 음료 가격 수정
	void editBeveragePrice(int beverageNo, int newBeveragePrice);
	// 음료 삭제
	void removeBeverage(int beverageNo);
	// 음료 리스트 보기
	List<Beverage> getBeverages();
	// 음료 상세 정보
	Beverage getBeverage(int beverageNo);
}
