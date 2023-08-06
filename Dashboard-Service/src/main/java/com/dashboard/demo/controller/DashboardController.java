package com.dashboard.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dashboard.demo.globalexceptionhandler.GenericException;
import com.dashboard.demo.model.OrderDetails;
import com.dashboard.demo.model.User;
import com.dashboard.demo.service.DashboardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dashboard")
@Slf4j
public class DashboardController {
	
	@Value("${service.user.internal.url}")
	private String userUrl;
	
	@Value("${service.payment.internal.url}")
	private String paymentUrl;
	
	@Value("${service.restaurant.internal.url}")
	private String restaurantUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DashboardService dashboardService;
	
	@GetMapping("/ping")
	public ResponseEntity<?> ping(){
		log.info("Entered into dashboard controller#Dashboard-Service");
		
		String res = restTemplate.getForObject(userUrl+"/users/ping",String.class);
		
		res = res + restTemplate.getForObject(paymentUrl+"/payments/ping", String.class);
		
		res = res + restTemplate.getForObject(restaurantUrl+"/restaurants/ping", String.class);
		
		log.info("Order Response#DashboardController :: {} ",res);
		return ResponseEntity.ok("Dashboard Service Pinging..."+res);
	}
	
	@GetMapping("users/{user-id}")
	public ResponseEntity<?> getUser(@PathVariable("user-id")String userId) {
		log.info("Entered into dashboard getUser#DashboardController");
		
		User user= restTemplate.getForObject(userUrl+"/users/"+userId,User.class);
		if(user == null) {
			throw new GenericException("User Not Found with this id :: {} "+userId);
		}
		return new ResponseEntity<>(user,HttpStatus.OK);
		
	}

	@PostMapping("order")
	public ResponseEntity<?> order(@RequestBody OrderDetails orderDetails){
		log.info("Entered into dashboard order#DashboardController");
		return new ResponseEntity<>(dashboardService.processOrder(orderDetails),HttpStatus.OK);
	}
}
