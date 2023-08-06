package com.dashboard.demo.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Food {
	
	private Integer foodId;
	private String name;
	private Double price;
	private Restaurant restaurant;
	

}
