package com.example.vendingMachine.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String add(@RequestParam(name = "errMsg", required = false) String errMsg, 
    				  Model model) {
    	log.debug("errMsg={}", errMsg);
    	if (errMsg != null && !errMsg.equals("")) {
	        model.addAttribute("errMsg", errMsg);
	    }
    	
        return "addBeverage";
    }
    
    @PostMapping("/add")
    public String add(Beverage beverage,
    				  RedirectAttributes redirectAttributes) {
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

    @GetMapping("/edit/{beverageNo}")
    public String editBeverage(@PathVariable int beverageNo, 
    						Model model,
    						@RequestParam(name = "errMsg", required = false) String errMsg) {
    	
        log.debug("beverageNo={}", beverageNo);
        // 음료 상세 메서드
        Beverage beverage = vendingMachineService.getBeverage(beverageNo);
        // model에 추가
        model.addAttribute("beverage", beverage);
        
        log.debug("errMsg={}", errMsg);
        if (errMsg != null && !errMsg.equals("")) {
	        model.addAttribute("errMsg", errMsg);
	    }
        
        return "editBeverage";
    }
    
    @PostMapping("/edit/{beverageNo}")
    public String editBeverage(@PathVariable("beverageNo") int beverageNo,
    						   @ModelAttribute Beverage newBeverage,
    						   RedirectAttributes redirectAttributes) {
    	
    	log.debug("beverageNo={}", beverageNo);
    	log.debug("newBeverage={}", newBeverage);
    	
    	boolean successEdit = vendingMachineService.editBeverage(newBeverage);
    	log.debug("successEdit={}", successEdit);
    	
    	if(!successEdit) {
    		redirectAttributes.addFlashAttribute("errMsg", "음료 수정이 실패했습니다. 다시 수정해주세요");
    		return "redirect:/beverageBoard/edit/{beverageNo}";
    	}
    	
    	return "redirect:/beverageBoard";
    }
    
    @PostMapping("/remove")
    public String remove(@RequestParam int beverageNo) {
    	log.debug("제거할 beverageNo={}", beverageNo);
    	vendingMachineService.removeBeverage(beverageNo);
    	
    	return "redirect:/beverageBoard";
    }
}
