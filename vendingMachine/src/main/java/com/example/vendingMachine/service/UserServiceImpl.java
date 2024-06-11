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
		
		int change = totalMoney - beveragePrice;
		// 거스름돈 반환 알고리즘
		Money changeMoney = new Money();
		int changeMoney10000 = change / 10000;
		int changeMoney5000 = (change % 10000) / 5000;
		int changeMoney1000 = (change % 5000) / 1000;
		int changeMoney500 = (change % 1000) / 500;
		int changeMoney100 = (change % 500) / 100;
		
		changeMoney.setMoney10000Cnt(changeMoney10000);
		changeMoney.setMoney5000Cnt(changeMoney5000);
		changeMoney.setMoney1000Cnt(changeMoney1000);
		changeMoney.setMoney500Cnt(changeMoney500);
		changeMoney.setMoney100Cnt(changeMoney100);
		
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
		beverageMapper.updateBeverageStock(editBeverage);
	}


}
