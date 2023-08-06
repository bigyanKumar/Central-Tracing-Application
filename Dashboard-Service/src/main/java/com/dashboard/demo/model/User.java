package com.dashboard.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

	private Integer id;
	private String name;
	private String email;
	private String mobile;
	private UserAddress address;
	
	
}
