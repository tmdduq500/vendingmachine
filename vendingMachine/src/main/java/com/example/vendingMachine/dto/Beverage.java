package com.example.vendingMachine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Beverage {
	
	private int beverageNo;
	private String beverageName;
	private String beverageType;
	private int beveragePrice;
	private int beverageStock;
	private String createDate;
	private String updateDate;
}
