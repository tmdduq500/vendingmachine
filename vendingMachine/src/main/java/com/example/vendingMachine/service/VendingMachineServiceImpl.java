package com.example.vendingMachine.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vendingMachine.dto.Beverage;
import com.example.vendingMachine.mapper.BeverageMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class VendingMachineServiceImpl implements VendingMachineService{

	private final BeverageMapper beverageMapper;
	
	@Override
	public void addBeverage(Beverage beverage) {
		// 음료 추가 후 결과 행 수 반환
		int row = beverageMapper.insertBeverage(beverage);
		log.debug("row={}", row);
		
		// row가 1이 아닐경우(입력 실패시) transcational 발동 - 메서드 및 쿼리 rollback
		if(row != 1) {
			throw new RuntimeException();
		}
	}

	@Override
	public void editBeverageStock(int beverageNo, int newBeverageStock) {
		log.debug("newBeverageStock={}", newBeverageStock);
		// newBeverageStock이 0보다 작을경우
		if(newBeverageStock < 0) {
			throw new RuntimeException();
		}
		
		Beverage editBeverage = beverageMapper.selectBeverage(beverageNo);
		editBeverage.setBeverageStock(newBeverageStock);
		
		int row = beverageMapper.updateBeverageStock(editBeverage);
			
		log.debug("row={}", row);
		
		// row가 1이 아닐경우(수정 실패시) transcational 발동 - 메서드 및 쿼리 rollback
		if(row != 1) {
			throw new RuntimeException();
		}
	}

	@Override
	public void editBeveragePrice(int beverageNo, int newBeveragePrice) {
		log.debug("newBeverageStock={}", newBeveragePrice);
		// newBeveragePrice이 0보다 작을경우
		if(newBeveragePrice < 0) {
			throw new RuntimeException();
		}
		
		Beverage editBeverage = beverageMapper.selectBeverage(beverageNo);
		editBeverage.setBeveragePrice(newBeveragePrice);
		int row = beverageMapper.updateBeveragePrice(editBeverage);
		
		log.debug("row={}", row);
		
		// row가 1이 아닐경우(수정 실패시) transcational 발동 - 메서드 및 쿼리 rollback
		if(row != 1) {
			throw new RuntimeException();
		}
	}

	@Override
	public void removeBeverage(int beverageNo) {
		int row = beverageMapper.deleteBeverage(beverageNo);
		
		log.debug("row={}", row);
		
		// row가 1이 아닐경우(삭제 실패시) transcational 발동 - 메서드 및 쿼리 rollback
		if(row != 1) {
			throw new RuntimeException();
		}
	}

	@Override
	public List<Beverage> getBeverages() {
		List<Beverage> beverages = beverageMapper.selectBeverages();
		log.debug("beverages={}", beverages);
		return beverages;
	}

	@Override
	public Beverage getBeverage(int beverageNo) {
		return beverageMapper.selectBeverage(beverageNo);
	}

}
