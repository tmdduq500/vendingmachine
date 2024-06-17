package com.example.vendingMachine.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vendingMachine.dto.Beverage;
import com.example.vendingMachine.dto.Money;
import com.example.vendingMachine.mapper.BeverageMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final BeverageMapper beverageMapper;

	@Override
	public Money returnChange(int inputBeverageNo, Money money) {
		log.debug("inputBeverageNo={}, money={}", inputBeverageNo, money);

		int beveragePrice = beverageMapper.selectBeverage(inputBeverageNo).getBeveragePrice();
		int totalMoney = money.getTotalMoney();
		
		// 거스름돈 반환
		int change = totalMoney - beveragePrice;
		Money changeMoney = new Money();
		changeMoney.setChangeMoney(change);
		
		return changeMoney;
	}

	@Override
	public boolean canBuy(int beverageNo, Money money) {
		boolean result = true;
		
		int beveragePrice = beverageMapper.selectBeverage(beverageNo).getBeveragePrice();
		log.debug("money={}, beveragePrice={}", money, beveragePrice);
		int totalMoney = money.getTotalMoney();
		if(totalMoney < beveragePrice) {
			result = false;
		}
		
		return result;
	}

	@Override
	public void stockMinusOne(int inputBeverageNo) {
		Beverage editBeverage = beverageMapper.selectBeverage(inputBeverageNo);
		int editBeverageStock = editBeverage.getBeverageStock() - 1;
		editBeverage.setBeverageStock(editBeverageStock);
		beverageMapper.updateBeverage(editBeverage);
	}


}
