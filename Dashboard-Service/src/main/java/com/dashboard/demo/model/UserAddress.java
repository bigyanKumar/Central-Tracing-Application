package com.dashboard.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAddress {
	
	private Integer id;
	private String addressLine1;
	private String addressLine2;
	private String State;

}
