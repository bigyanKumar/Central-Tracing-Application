package com.restaurant.demo.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.restaurant.demo.model.DashboardNotificationDto;
import com.restaurant.demo.model.UserNotificationDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkMessagePublisher {
	
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	
	public void sendMessageForTheUser(String message) {
		
	 ListenableFuture<SendResult<String, Object>> future = template.send("notification-msg",message);
	 
	 future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
         @Override
         public void onSuccess(SendResult<String, Object> result) {
             log.info("Message sent successfully: " + result.getRecordMetadata().toString());
         }
         @Override
         public void onFailure(Throwable ex) {
             log.info("Failed to send message: " + ex.getMessage());
         }
     });
	} 
	 
	public void sendMessageForTheService(String message) {
			
		 ListenableFuture<SendResult<String, Object>> future = template.send("dashboard-msg",message);
		 
		 future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
	         @Override
	         public void onSuccess(SendResult<String, Object> result) {
	             log.info("Message sent successfully: " + result.getRecordMetadata().toString());
	         }
	         @Override
	         public void onFailure(Throwable ex) {
	             log.info("Failed to send message: " + ex.getMessage());
	         }
	     });
	 }
	
	public void sendMessageForTheDashBoardService(DashboardNotificationDto message) {
		
		 ListenableFuture<SendResult<String, Object>> future = template.send("dashboard",message);
		 
		 future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
	         @Override
	         public void onSuccess(SendResult<String, Object> result) {
	             log.info("Message sent successfully: " + result.getRecordMetadata().toString());
	         }
	         @Override
	         public void onFailure(Throwable ex) {
	             log.info("Failed to send message: " + ex.getMessage());
	         }
	     });
	 }
	
	public void sendNotificationForTheUser(UserNotificationDto message) {
		
		 ListenableFuture<SendResult<String, Object>> future = template.send("notification",message);
		 
		 future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
	         @Override
	         public void onSuccess(SendResult<String, Object> result) {
	             log.info("Message sent successfully: " + result.getRecordMetadata().toString());
	         }
	         @Override
	         public void onFailure(Throwable ex) {
	             log.info("Failed to send message: " + ex.getMessage());
	         }
	     });
	} 
	

}
