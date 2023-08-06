package com.dashboard.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestOrderDetail {
	
	private Integer id;
	private Integer userId;
	private String userEmail;
	private String userMobile;
	private String userName;
	private Integer foodId;
	private Integer orderDetailsId;

}
