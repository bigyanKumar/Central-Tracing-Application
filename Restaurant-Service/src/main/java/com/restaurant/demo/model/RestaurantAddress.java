package com.restaurant.demo.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RestaurantAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String addressLine1;
	private String addressLine2;
	private String State;

}
