package com.example.vendingMachine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Money {
	private int money100Cnt = 0;
	private int money500Cnt = 0;
	private int money1000Cnt = 0;
	private int money5000Cnt = 0;
	private int money10000Cnt = 0;
	
	public int getTotalMoney() {
		return this.money100Cnt * 100+
			   this.money500Cnt * 500+
			   this.money1000Cnt * 1000+ 
			   this.money5000Cnt * 5000+ 
			   this.money10000Cnt * 10000;
	}
}
