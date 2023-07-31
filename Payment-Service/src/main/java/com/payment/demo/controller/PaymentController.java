package com.payment.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/payments")
@Slf4j
public class PaymentController{
	
	@GetMapping("/ping")
	public ResponseEntity<?> ping(){
		log.info("Enter into payment controller#Payment-Serivice");
		
		log.info("Payment received sucessfully.");
		
		return ResponseEntity.ok("  Payment received sucessfully.  ");
	}
	
	@GetMapping("/validate/{user-id}/{amount}")
	public ResponseEntity<?> payment(@PathVariable("user-id") Integer userId,@PathVariable("amount") Double amount){
		log.info("Enter into payment#Payment-Controller");
		
		log.info("Payment done sucessfully amount:: {} , userId ::{}",amount,userId);
		
		return ResponseEntity.ok(true);
	}

}
