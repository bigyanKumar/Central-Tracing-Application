package com.dashboard.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dashboard.demo.model.OrderDetails;

@Repository
public interface OrderDetailsDao extends JpaRepository<OrderDetails, Integer> {

}
