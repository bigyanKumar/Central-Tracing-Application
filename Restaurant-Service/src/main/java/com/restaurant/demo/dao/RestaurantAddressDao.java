package com.restaurant.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.demo.model.RestaurantAddress;

@Repository
public interface RestaurantAddressDao extends JpaRepository<RestaurantAddress, Integer>{

}
