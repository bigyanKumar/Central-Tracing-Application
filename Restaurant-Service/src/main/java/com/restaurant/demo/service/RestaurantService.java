package com.restaurant.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.restaurant.demo.common.KafkMessagePublisher;
import com.restaurant.demo.dao.*;
import com.restaurant.demo.globalexceptionhandler.GenericException;
import com.restaurant.demo.model.*;
import brave.Tracer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestaurantService {

	@Autowired
	private FoodDao foodDao;
	
	@Autowired
	private RestaurantDao restaurantDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private KafkMessagePublisher kafkMessagePublisher;
	
	@Autowired
	private Tracer tracer;
	
	public Restaurant saveRestaurant(Restaurant restaurant) throws GenericException{
		log.info("Entered into saveRestaurant#RestaurantService");
		try {
			return restaurantDao.save(restaurant);
			
		}catch(Exception ex) {
			log.info("Exception occurred while saving the data saveRestaurant#RestaurantService :: {}",ex.getLocalizedMessage());
			throw new GenericException("Unable to save the data");
		}	
	}
	
	public Food addFood(Food food,Integer restaurantId) throws GenericException {
		log.info("Entered into addFood#RestaurantService");
		try {
			Optional<Restaurant> restaurant = restaurantDao.findById(restaurantId);
			
			restaurant.orElseThrow(()-> new GenericException("Invalid restaurant id please give right details "+restaurant));
			food.setRestaurant(restaurant.get());
			return foodDao.save(food);	
		}catch(Exception ex){
			log.info("Exception occurred while saving the data saveRestaurant#RestaurantService :: {}",ex.getLocalizedMessage());
			throw new GenericException("Unable to save the data");
		}
	}
	
	public List<Food> getFoods(){
		log.info("Entered into getFoods#RestaurantService");
		try {
			
			return foodDao.findAll();
			
		}catch(Exception ex) {
			log.info("Exception occurred while getting the data getFoods#RestaurantService :: {}",ex.getLocalizedMessage());
			throw new GenericException("Unable to get the data");
		}
	}
	public List<Restaurant> getRestaurant(){
		log.info("Entered into getRestaurant#RestaurantService");
		try {
			
			return restaurantDao.findAll();
			
		}catch(Exception ex) {
			log.info("Exception occurred while getting the data getRestaurants#RestaurantService :: {}",ex.getLocalizedMessage());
			throw new GenericException("Unable to get the data");
		}
	}
	
	public Food getFood(Integer foodId){
		log.info("Entered into getFood#RestaurantService");
		try {
			return foodDao.findById(foodId).get();
			
		}catch(Exception ex) {
			log.info("Exception occurred while getting the data getFood#RestaurantService :: {}",ex.getLocalizedMessage());
			throw new GenericException("Unable to get the data");
		}
	}
	@Async
	@Transactional
	public void placeOrder(OrderRequest order) throws Exception {
		log.info("Entered into placeOrder#RestaurantService");
		log.info("OrderRequest body placeOrder#RestaurantService ::{}",order);
		try {
	        processOrder(order);
	    } catch (Exception ex) {
	        tracer.currentSpan().tag("error", "true");
	        tracer.currentSpan().tag("error.message", ex.getMessage());
	        tracer.currentSpan().error(ex);
	        log.info("Exception came in placeHolder#Restaurant-Service  :: {} ", ExceptionUtils.getStackTrace(ex));
	        throw ex;
	    }
	}
	
	public void processOrder(OrderRequest order) throws InterruptedException {
		TimeUnit.MINUTES.sleep(order.getWait());
		order.setStatus(OrderStatus.PROCESSED);
		orderDao.save(order);
		kafkMessagePublisher.sendMessageForTheDashBoardService(
		new DashboardNotificationDto(order.getOrderDetails().getOrderDetailsId(),order.getStatus().name()));
		kafkMessagePublisher.sendNotificationForTheUser(
		new UserNotificationDto(
				order.getOrderDetails().getUserId(),order.getOrderDetails().getFoodId(),
				order.getOrderDetails().getUserName(),order.getOrderDetails().getUserEmail(),order.getOrderDetails().getUserMobile()));
		log.info("All messages has been pushed to the kafka.");
	}
	
	
}
