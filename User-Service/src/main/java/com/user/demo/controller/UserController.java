package com.user.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.user.demo.globalexceptionhandler.GenericException;
import com.user.demo.model.User;
import com.user.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
	

	@Value("${service.payment.internal.url}")
	private String paymentInternalUrl;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/ping")
	public ResponseEntity<?> ping(){
		log.info("Enter into user controller#User-Service");
		
		log.info("User Validated sucessfully.");
		
		return ResponseEntity.ok("User Validated sucessfully.  ");
	}
	
	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody User user){
		log.info("Entered into saveUser#UserController");
		
		return new ResponseEntity<>(userService.saveUser(user),HttpStatus.OK);
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getUser(@PathVariable("id")Integer id) throws GenericException  {
		log.info("Entered into getUser#UserController");
		return new ResponseEntity<>(userService.getUser(id),HttpStatus.OK);
	}
	

}
