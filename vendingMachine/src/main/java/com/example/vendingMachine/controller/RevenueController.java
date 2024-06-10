package com.example.vendingMachine.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.vendingMachine.service.RevenueService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RevenueController {
	
	private final RevenueService revenueService;
	
	@GetMapping("/revenues")
	public String revenues(Model model) {
		List<Map<String, Object>> revenues = revenueService.getRevenues();
		log.debug("revenues={}", revenues);
		
		model.addAttribute("revenues", revenues);
		return "revenues";
	}
}
