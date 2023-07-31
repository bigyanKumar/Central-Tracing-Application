package com.dashboard.demo.common;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.restaurant.demo.model.DashboardNotificationDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaMessageListener {
	
	@KafkaListener(topics = "dashboard-msg",groupId = "dashboard-service")
	public void consume(String message) {
		
		log.info("Dashboard-Service#Message are getting from the kafak :: {}",message);
		
	}
	
	@KafkaListener(topics = "dashboard",groupId = "dashboard-service")
	public void consumeMessage(DashboardNotificationDto message) {
		
		log.info("Dashboard-Service#Message are getting from the kafak :: {}",message.toString());
		
	}

}
