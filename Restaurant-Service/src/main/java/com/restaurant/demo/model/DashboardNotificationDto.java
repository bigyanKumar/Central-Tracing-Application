package com.restaurant.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DashboardNotificationDto {
	
	private Integer OrderDetailsId;
	private String status;

}
