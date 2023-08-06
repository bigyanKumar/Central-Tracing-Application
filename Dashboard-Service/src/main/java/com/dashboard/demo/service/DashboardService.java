package com.dashboard.demo.service;

import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dashboard.demo.dao.OrderDetailsDao;
import com.dashboard.demo.globalexceptionhandler.GenericException;
import com.dashboard.demo.model.*;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DashboardService {

	@Autowired
	private OrderDetailsDao orderDetailsDao;
	
	@Value("${service.user.internal.url}")
	private String userUrl;
	
	@Value("${service.payment.internal.url}")
	private String paymentUrl;
	
	@Value("${service.restaurant.internal.url}")
	private String restaurantUrl;

	@Autowired
	private RestTemplate restTemplate;

	
//	log.info("Enter into SaveUser#UserService");
	
	public OrderDto processOrder(OrderDetails orderdetails) {
		log.info("Entered into processOrder#DashBoard-Service requestBody :: {}",orderdetails.toString());
		OrderDto orderDto=new OrderDto();
		User user= getUser(orderdetails.getUserDetails().getUserId());
		validatePayment(orderdetails.getUserDetails().getUserId(),orderdetails.getFoodDetails().getFoodPrice());
		log.info("Payment received sucessfully#processOrder#Dashoboard-Service");
		Food food = getFood(orderdetails.getFoodDetails().getFoodId());
		OrderDetails orderDetails=orderDetailsDao.save(dataMapping(orderdetails,user,food));
		log.info("Data saved for the order processOrder#DashboardService :: {}",orderDetails);
		String url= restaurantUrl+"/restaurants/order";
		try {
			OrderRequest order=new OrderRequest();
			order.setOrderedTime(LocalDateTime.now());
			order.setOrderDetails(detailsMapping(orderDetails));
			HttpHeaders headers=new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			HttpEntity request=new HttpEntity(order,headers);
			log.info("Generate Order payload :: {} , url :: {}",request,url);
			ResponseEntity<OrderRequest> res=restTemplate.exchange(url, HttpMethod.POST,request,OrderRequest.class);
			orderDto.setStatus(res.getBody().getStatus().name());
			orderDto.setId(orderDetails.getOrderId());
			log.info("Order Response :: {},OrderDto body :: {} ",res.getBody(),orderDto.toString());
			if(!res.getBody().getStatus().name().equals(Status.PROCESSED.name()))
				orderDto.setDescription("Please wait for "+res.getBody().getWait()+ " Min(s), We are processing your. Will notify sortly...");
			else
			orderDto.setDescription("Hurrah! Your order has been processed");
		}catch(Exception ex){
			log.info("Exception in processOrder#DashboredService",ex.getLocalizedMessage());
			throw new GenericException("Sorry for the innocence caused, Please retry after some time");
		}
		log.info("OrderDetails Body :: {} ",orderDetails.toString());
		return orderDto;
		
	}
	public RequestOrderDetail detailsMapping(OrderDetails orderDetails) {
		log.info("Enter into  detailsMapping#Dashboard-Service");
		RequestOrderDetail details=new RequestOrderDetail();
		details.setFoodId(orderDetails.getFoodDetails().getFoodId());
		details.setUserId(orderDetails.getUserDetails().getUserId());
		details.setUserName(orderDetails.getUserDetails().getName());
		details.setUserEmail(orderDetails.getUserDetails().getEmail());
		details.setUserMobile(orderDetails.getUserDetails().getMobile());
		details.setOrderDetailsId(orderDetails.getOrderId());
		return details;
	}
	
	public OrderDetails dataMapping(OrderDetails order,User user, Food food) {
		log.info("Enter into  dataMapping#Dashboard-Service");
		order.setOrderedAt(LocalDateTime.now());
		order.setStatus(Status.PROCESSING);
		order.getFoodDetails().setFoodName(food.getName());
		order.getFoodDetails().setRestaurantId(food.getRestaurant().getRestaurantId());
		order.getUserDetails().setEmail(user.getEmail());
		order.getUserDetails().setMobile(user.getMobile());
		order.getUserDetails().setName(user.getName());
		log.info("Data Mapped in dataMapping#DashboaredService :: {}",order);
		return order;
	}
	public Food getFood(int foodId) {
		log.info("Enter into  getFood#Dashboard-Service :: {} ",foodId);
		Food food= restTemplate.getForObject(restaurantUrl+"/restaurants/foods/"+foodId,Food.class);
		log.info("food Response :: {} ",food);
		if(food==null) {
			log.info("Food Not found with this id "+foodId);
			throw new GenericException("Please provice valid user id, User not foud with this id "+foodId);
		}
		return food;
	}
	
	public void validatePayment(int userId,Double amount) {
		log.info("Enter into validatePayment#Dashboard-Service :: {}",userId,amount);
		
		Boolean valid = restTemplate.getForObject(paymentUrl+"/payments/validate/"+userId+"/"+amount, Boolean.class);
		log.info("Payment Response :: {} ",valid);
		if(valid==null && valid == false) {
			log.info("Payment failed please validate again");
			throw new GenericException("payment got fail please retry..");
		}
		
	}
	
	public User getUser(int userId) {
		log.info("Enter into getUser#Dashboard-Service :: {}",userId);
		User user= restTemplate.getForObject(userUrl+"/users/"+userId,User.class);
		log.info("User Response :: {} ",user);
		if(user==null) {
			log.info("User Not found with this id "+userId);
			throw new GenericException("Please provice valid user id, User not foud with this id "+userId);
		}
		return user;
	}
	
	
	
}
