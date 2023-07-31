package com.restaurant.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.demo.model.Food;

@Repository
public interface FoodDao extends JpaRepository<Food, Integer> {

}
