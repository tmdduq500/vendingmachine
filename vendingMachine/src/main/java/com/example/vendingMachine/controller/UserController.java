package com.example.vendingMachine.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.vendingMachine.dto.Beverage;
import com.example.vendingMachine.dto.Money;
import com.example.vendingMachine.service.RevenueService;
import com.example.vendingMachine.service.UserService;
import com.example.vendingMachine.service.VendingMachineService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

	private final VendingMachineService vendingMachineService;
	private final UserService userService;
	private final RevenueService revenueService;
	
	@GetMapping("/beverages")
	public String beverages(@RequestParam(name = "errMsg", required = false) String errMsg,
							@RequestParam(name = "buyBeverageName", required = false) String buyBeverageName,
							@ModelAttribute("changeMoney") Money changeMoney,
							Model model) {
		// 음료 리스트 가져오기
		List<Beverage> beverages = vendingMachineService.getBeverages();
		log.debug("beverages={}", beverages);
		// model에 추가
		model.addAttribute("beverages", beverages);
		
		log.debug("errMsg={}", errMsg);
		log.debug("buyBeverageName={}", buyBeverageName);
		log.debug("changeMoney={}", changeMoney);
		
	    if (errMsg != null && !errMsg.equals("")) {
	        model.addAttribute("errMsg", errMsg);
	    }
	    log.debug("totalChangeMoney={}", changeMoney.getTotalMoney());
	    
	    if(changeMoney != null && changeMoney.getTotalMoney() != 0) {
		    model.addAttribute("changeMoney", changeMoney);	    	
	    }
	    
	    if (buyBeverageName != null && !buyBeverageName.equals("")) {
	        model.addAttribute("buyBeverageName", buyBeverageName);
	    }

		return "beverages";
	}
	
	@PostMapping("/buy")
	public String buyBeverage(@RequestParam int inputBeverageNo, RedirectAttributes redirectAttributes, 
							  Money money) throws Exception {
		log.debug("inputBeverageNo={}", inputBeverageNo);
		log.debug("money={}", money);
		
		// 음료 구매
		// 1. 투입 금액, 음료 가격 비교 - 구매 불가하다면 알림메시지반환
		
		if(!userService.canBuy(inputBeverageNo, money)) {
			redirectAttributes.addFlashAttribute("errMsg", "금액이 부족합니다");
			return "redirect:/beverages";
		}
		
		// 구매 가능하다면
		// 2. 음료 재고 수정
		userService.stockMinusOne(inputBeverageNo);
		// 3. 거스름돈 반환
		Money changeMoney = userService.returnChange(inputBeverageNo, money);
		log.debug("changeMoney={}", changeMoney);
		
		// 음료 구매 시 어떤 음료인지 beverage 뷰에 값 보내기
		String buyBeverageName = vendingMachineService.getBeverage(inputBeverageNo).getBeverageName();
		redirectAttributes.addFlashAttribute("buyBeverageName", buyBeverageName);
		
		// 거스름돈이 있다면
		if (changeMoney != null && changeMoney.getTotalMoney() != 0) {
			// redirectAttributes.addFlashAttribute - 휘발성 redirectAttribute()
            redirectAttributes.addFlashAttribute("changeMoney", changeMoney);
            log.debug("changeMoney={}", changeMoney);
        }
		// revenue 테이블에 수익 추가
		revenueService.addRevenue(inputBeverageNo);
		
		return "redirect:/beverages";
	}
}
