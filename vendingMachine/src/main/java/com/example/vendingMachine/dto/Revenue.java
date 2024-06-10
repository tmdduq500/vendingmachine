package com.example.vendingMachine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Revenue {

	private int revenueNo;
	private int beverageNo;
	private int beveragePrice;
	private String createDate;
	
	
	public Revenue(int beverageNo, int beveragePrice) {
		this.beverageNo = beverageNo;
		this.beveragePrice = beveragePrice;
	}
	
	
}
