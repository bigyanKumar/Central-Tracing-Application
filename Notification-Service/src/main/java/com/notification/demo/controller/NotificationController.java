package com.notification.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/notifications")
public class NotificationController {
	
	private static final Logger log = LoggerFactory.getLogger(NotificationController.class);
	
	@GetMapping("/ping")
	public ResponseEntity<?> ping(){
		log.info("Enter into notification controller#Notification-Service");
		
		log.info("Email and sms sent to the user.");
		
		return ResponseEntity.ok("  Notification genarated sucessfully....  ");
	}
	

}
