package com.example.vendingMachine.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendingMachine {

	private int vendingMachineNo;
	private List<Beverage> beverages;
}
