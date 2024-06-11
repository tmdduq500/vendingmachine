package com.example.vendingMachine.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.vendingMachine.dto.Beverage;
import com.example.vendingMachine.service.VendingMachineService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/beverageBoard")
@Controller
public class VendingMachineController {

    private final VendingMachineService vendingMachineService;

    @GetMapping
    public String beverages(Model model) {
        // 음료 리스트 가져오기
        List<Beverage> beverages = vendingMachineService.getBeverages();
        log.debug("beverages={}", beverages);
        // model에 추가
        model.addAttribute("beverages", beverages);

        return "beverageBoard";
    }

    @GetMapping("/add")
    public String add(@RequestParam(name = "errMsg", required = false) String errMsg, Model model) {
    	log.debug("errMsg={}", errMsg);
    	if (errMsg != null && !errMsg.equals("")) {
	        model.addAttribute("errMsg", errMsg);
	    }
    	
        return "addBeverage";
    }
    
    @PostMapping("/add")
    public String add(Beverage beverage, RedirectAttributes redirectAttributes) {
    	log.debug("추가한 beverage={}", beverage);
    	
    	// 음료 추가 
    	boolean successAdd = vendingMachineService.addBeverage(beverage);
    	
    	// 추가 실패했다면
    	if(!successAdd) {
    		redirectAttributes.addFlashAttribute("errMsg", "음료 가격은 100원 단위이상으로 설정 해주세요");
    		return "redirect:/beverageBoard/add";
    	}
    	
        return "redirect:/beverageBoard";
    }

    @GetMapping("/editPrice/{beverageNo}")
    public String editPrice(@PathVariable int beverageNo, Model model) {
        log.debug("beverageNo={}", beverageNo);
     // 음료 상세 메서드
        Beverage beverage = vendingMachineService.getBeverage(beverageNo);
        // model에 추가
        model.addAttribute("beverage", beverage);
        
        return "editPrice";
    }
    
    @PostMapping("/editPrice/{beverageNo}")
    public String editPrice(@PathVariable int beverageNo,
    			  			@RequestParam int newBeveragePrice) {
    	log.debug("beverageNo={}", beverageNo);
    	log.debug("newBeveragePrice={}", newBeveragePrice);
    	
    	vendingMachineService.editBeveragePrice(beverageNo, newBeveragePrice);
    	
    	return "redirect:/beverageBoard";
    }

    @GetMapping("/editStock/{beverageNo}")
    public String editStock(@PathVariable int beverageNo, Model model) {
        log.debug("beverageNo={}", beverageNo);
        // 음료 상세 메서드
        Beverage beverage = vendingMachineService.getBeverage(beverageNo);
        // model에 추가
        model.addAttribute("beverage", beverage);
        
        return "editStock";
    }
    
    @PostMapping("/editStock/{beverageNo}")
    public String editStock(@PathVariable int beverageNo,
    			  			@RequestParam int newBeverageStock) {
    	log.debug("beverageNo={}", beverageNo);
    	log.debug("newBeverageStock={}", newBeverageStock);
    	
    	vendingMachineService.editBeverageStock(beverageNo, newBeverageStock);
    	
    	return "redirect:/beverageBoard";
    }
    
    @PostMapping("/remove")
    public String remove(@RequestParam int beverageNo) {
    	log.debug("제거할 beverageNo={}", beverageNo);
    	vendingMachineService.removeBeverage(beverageNo);
    	
    	return "redirect:/beverageBoard";
    }
}
