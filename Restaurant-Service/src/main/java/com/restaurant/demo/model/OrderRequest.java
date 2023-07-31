package com.restaurant.demo.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class OrderRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	private Integer wait;
	private LocalDateTime orderedTime;
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	@OneToOne(cascade = CascadeType.ALL)
	private RequestOrderDetail orderDetails;

}

