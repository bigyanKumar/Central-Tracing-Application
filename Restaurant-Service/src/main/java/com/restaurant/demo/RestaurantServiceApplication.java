package com.restaurant.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync (proxyTargetClass = true)
public class RestaurantServiceApplication {

	 public static void main(String[] args) {
		SpringApplication.run(RestaurantServiceApplication.class, args);
	}

}
