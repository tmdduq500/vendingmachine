package com.example.vendingMachine.service;

import com.example.vendingMachine.dto.Money;

public interface UserService {
	
	// 잔돈 반환
	Money returnChange(int inputBeverageNo, Money money);

	// 투입 금액, 음료 가격 비교
	boolean canBuy(int beverageNo, Money money);
	
	// 재고 하나 빼기
	void stockMinusOne(int inputBeverageNo);
}
