package com.restaurant.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.demo.model.RequestOrderDetail;

public interface OrderDetailsDao extends JpaRepository<RequestOrderDetail, Integer> {

}
