package com.user.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.demo.dao.UserDao;
import com.user.demo.globalexceptionhandler.GenericException;
import com.user.demo.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public User saveUser(User user) {
		log.info("Enter into SaveUser#UserService");
		try {
			return userDao.save(user);
		}catch(Exception ex) {
			log.info("Exception Occured in Database connectivity :: {}",ex.getLocalizedMessage());
			throw new GenericException(ex.getLocalizedMessage());
		}
	}
	
	public User getUser(Integer userId) throws GenericException{
		log.info("Enter into getUser#UserService");
		Optional<User>  user = userDao.findById(userId);
		user.orElseThrow(()-> new GenericException("User Not Found with this id "+userId));
		return user.get();
	}

}
