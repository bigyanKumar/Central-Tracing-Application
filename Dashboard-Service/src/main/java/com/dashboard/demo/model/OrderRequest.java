package com.dashboard.demo.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {
	
	private Integer orderId;
	private Integer wait;
	private LocalDateTime orderedTime;
	private Status status;
	private RequestOrderDetail orderDetails;

}

