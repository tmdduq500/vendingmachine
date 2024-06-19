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
	public boolean addBeverage(Beverage beverage) {
		boolean canAddBeverage = false;
		
		// 가격, 재고 유효성 검사 성공시 insertBeverage 메서드 실행
		if(validateBeverage(beverage)) {
			// 음료 추가 후 결과 행 수 반환
			int row = beverageMapper.insertBeverage(beverage);
			log.debug("row={}", row);
			
			// row가 1이 아닐경우(입력 실패시) transcational 발동 - 메서드 및 쿼리 rollback
			if(row != 1) {
				throw new RuntimeException();
			}
			
			canAddBeverage = true;
		}
		
		return canAddBeverage;
	}
	
	@Override
	public boolean editBeverage(Beverage newBeverage) {
		boolean canEditBeverage = false;
		
		log.debug("newBeverage={}", newBeverage);
		
		if(validateBeverage(newBeverage)) {
			int row = beverageMapper.updateBeverage(newBeverage);
			
			// row가 1이 아닐경우(수정 실패시) transcational 발동 - 메서드 및 쿼리 rollback
			if(row != 1) {
				throw new RuntimeException();
			}
			log.debug("row={}", row);
			
			canEditBeverage = true;
		}
		
		return canEditBeverage;
	}

	@Override
	public void removeBeverage(int beverageNo) {
//		int row = beverageMapper.deleteBeverage(beverageNo);
		
		// soft delete로 변경
		int row = beverageMapper.softDeleteBeverage(beverageNo);
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

	@Override
	public boolean validateBeverage(Beverage beverage) {
		int beveragePrice = beverage.getBeveragePrice();
		int beverageStock = beverage.getBeverageStock();
		
		if((beverageStock < 0) || (beveragePrice % 100 != 0)) {
			return false;
		}
		
		return true;
	}

}
