package com.notification.demo.common;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.common.dto.UserNotificationDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaMessageListener {
	
	@KafkaListener(topics = "notification-msg",groupId = "notifications")
	public void consume(String message) {
		
		log.info("Message are getting from the kafak :: {}",message);
		
	}
	
	@KafkaListener(topics = "notification",groupId = "notifications")
	public void consumeMsg(UserNotificationDto message) {
		log.info("Nessage are getting from the kafak KafkaMessageListener#NotificationService:: {}",message.toString());
		
	}
	

}
