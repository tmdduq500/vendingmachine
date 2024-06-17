package com.example.vendingMachine.service;

import java.util.List;

import com.example.vendingMachine.dto.Beverage;

public interface VendingMachineService {
	
	// 음료 추가
	boolean addBeverage(Beverage beverage);
	// 음료 정보 수정
	boolean editBeverage(int beverageNo, Beverage beverage);
	// 음료 삭제
	void removeBeverage(int beverageNo);
	// 음료 리스트 보기
	List<Beverage> getBeverages();
	// 음료 상세 정보
	Beverage getBeverage(int beverageNo);
	// 재고 유효성 검사
	boolean checkStock(int beverageStock);
	// 가격 유효성 검사
	boolean checkPrice(int beveragePrice);
}
