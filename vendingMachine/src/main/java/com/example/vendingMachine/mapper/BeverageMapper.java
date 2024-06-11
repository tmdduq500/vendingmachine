package com.example.vendingMachine.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.vendingMachine.dto.Beverage;

@Mapper
public interface BeverageMapper {
	
	// 음료 추가
	int insertBeverage(Beverage beverage);
	// 음료 재고 수정
	int updateBeverageStock(Beverage beverage);
	// 음료 가격 수정
	int updateBeveragePrice(Beverage beverage);
	// 음료 삭제
	int deleteBeverage(int beverageNo);
	// 음료 삭제(soft delete)
	int softDeleteBeverage(int beverageNo);
	// 음료 리스트 보기
	List<Beverage> selectBeverages();
	// 음료 상세 보기
	Beverage selectBeverage(int beverageNo);
}
