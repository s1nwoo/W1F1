package com.wifi.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wifi.client.service.KakaoPayService;

@Controller
public class KakaoPayConroller {
	
	@Autowired
	private KakaoPayService payment;
	
	@GetMapping("/kakaoPay")
	public String kakaoPay() {
		
		return "redirect:" +payment.requestPay();
	}

	@GetMapping("/successPay")
	public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model ) {
		
		model.addAttribute("successPay", payment.PaymentInfo(pg_token));
	}
	
	

}
