package com.restaurant.demo.controller;

import java.time.LocalDateTime;

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

import com.restaurant.demo.common.KafkMessagePublisher;
import com.restaurant.demo.model.Food;
import com.restaurant.demo.model.OrderRequest;
import com.restaurant.demo.model.OrderStatus;
import com.restaurant.demo.model.Restaurant;
import com.restaurant.demo.service.RestaurantService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/restaurants")
@Slf4j
public class RestaurantController {
	
	@Value("${service.notification.internal.url}")
	private String notificationUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private KafkMessagePublisher publisher;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping("/ping")
	public ResponseEntity<?> ping(){
		log.info("Entered into restaurant controller#Restaurant-Service");
		
		publisher.sendMessageForTheUser("Order placed please notify the user ");
		
		publisher.sendMessageForTheService("Order and notification both are completed.");
		
		return ResponseEntity.ok(" Order has been processed to the user ");
	}
	
	@PostMapping
	public ResponseEntity<?> saveRestaurantDetails(@RequestBody Restaurant restaurant){
		log.info("Entered into saveRestaurantDetails#Restaurant-Controller");
		
		return new ResponseEntity<>(restaurantService.saveRestaurant(restaurant),HttpStatus.OK);
		
	}
	@PostMapping("foods/{restaurant-id}")
	public ResponseEntity<?> saveFood(@RequestBody Food food,@PathVariable("restaurant-id")Integer id){
		log.info("Entered into saveRestaurantDetails#Restaurant-Controller");
		
		return new ResponseEntity<>(restaurantService.addFood(food,id),HttpStatus.OK);
		
	}
	
	@GetMapping 
	public ResponseEntity<?> getRestaurants(){
		log.info("Entered into getRestaurants#Restaurant-Controller");
		
		return new ResponseEntity<>(restaurantService.getRestaurant(),HttpStatus.OK);
	}
	
	@GetMapping("foods")
	public ResponseEntity<?> getFoods(){
		log.info("Entered into getFoods#Restaurant-Controller");
		
		return new ResponseEntity<>(restaurantService.getFoods(),HttpStatus.OK);
	}
	
	@GetMapping("foods/{food-id}")
	public ResponseEntity<?> getFoods(@PathVariable("food-id")Integer id){
		log.info("Entered into restaurant getFood#Restaurant-Controller");
		return new ResponseEntity<>(restaurantService.getFood(id),HttpStatus.OK);
	}
	
	@PostMapping("order")
	public ResponseEntity<?> order(@RequestBody OrderRequest order) throws Exception{
		log.info("Entered into  Order#Restaurant-Controller");
		OrderRequest order1 = order;
		int min = (int)Math.random()*(5-1+1)+1; 
		order1.setWait(min);
		order1.setStatus(OrderStatus.PENDING);
		order1.setOrderedTime(LocalDateTime.now());
		restaurantService.placeOrder(order1);
		return new ResponseEntity<>(order1,HttpStatus.OK);
	}

}
