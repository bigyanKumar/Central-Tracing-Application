package com.restaurant.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.demo.model.OrderRequest;

@Repository
public interface OrderDao extends JpaRepository<OrderRequest, Integer> {

}
