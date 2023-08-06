package com.dashboard.demo.common;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.common.dto.DashboardNotificationDto;
import com.dashboard.demo.dao.OrderDetailsDao;
import com.dashboard.demo.globalexceptionhandler.GenericException;
import com.dashboard.demo.model.OrderDetails;
import com.dashboard.demo.model.Status;
import brave.Tracer;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaMessageListener {
	
	@Autowired
	private Tracer tracer;
	
	@Autowired
	private OrderDetailsDao orderDetailsDao;
	
	@KafkaListener(topics = "dashboard-msg",groupId = "dashboard-service")
	public void consume(String message) {
		
		log.info("Dashboard-Service#Message are getting from the kafak :: {}",message);
		
	}
	
	@KafkaListener(topics = "dashboard",groupId = "dashboard-service")
	public void consumeMessageService1(DashboardNotificationDto message) {
		log.info("Dashboard-Service#Message are getting from the kafaklistener 1 :: {}",message.toString());
		updateStatus(message);
	}
	@KafkaListener(topics = "dashboard",groupId = "dashboard-service")
	public void consumeMessageService2(DashboardNotificationDto message) {
		log.info("Dashboard-Service#Message are getting from the kafaklistener 2 :: {}",message.toString());
		updateStatus(message);
	}
	@KafkaListener(topics = "dashboard",groupId = "dashboard-service")
	public void consumeMessageService3(DashboardNotificationDto message) {
		log.info("Dashboard-Service#Message are getting from the kafaklistener 3 :: {}",message.toString());
		updateStatus(message);
	}
	@KafkaListener(topics = "dashboard",groupId = "dashboard-service")
	public void consumeMessageService4(DashboardNotificationDto message) {
		log.info("Dashboard-Service#Message are getting from the kafaklistener 4 :: {}",message.toString());
		updateStatus(message);
	}
	@KafkaListener(topics = "dashboard",groupId = "dashboard-service")
	public void consumeMessageService5(DashboardNotificationDto message) {
		log.info("Dashboard-Service#Message are getting from the kafaklistener 5 :: {}",message.toString());
		updateStatus(message);
	}
	
	@Async
	@Transactional
	public void updateStatus(DashboardNotificationDto data) {
		log.info("Entered into statusUpdate#KafkaMessageListener ");
		try {
			if(data.getOrderDetailsId()!=null) {
				Optional<OrderDetails> orderDetils = orderDetailsDao.findById(data.getOrderDetailsId());
				orderDetils.orElseThrow(()->new GenericException("Order Detils is not found with this id :: {}"+data.getOrderDetailsId()));
				OrderDetails order=orderDetils.get();
				order.setStatus(Status.PROCESSED);
				order.setOrdereDone(LocalDateTime.now());
				orderDetailsDao.save(order);
			}else {
				log.info("Data is not found with this object");
			}
		}catch(Exception ex) {
			tracer.currentSpan().tag("error", "true");
			tracer.currentSpan().error(ex);
			log.info("Exception came while updating the status updateStatus#KafkaMessageListener :: {}",ExceptionUtils.getStackTrace(ex));
			throw ex;
		}
	}

}
