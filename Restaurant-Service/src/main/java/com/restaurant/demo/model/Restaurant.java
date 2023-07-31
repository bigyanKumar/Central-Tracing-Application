package com.restaurant.demo.model;


import javax.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer restaurantId;
	private String name;
	@OneToOne(cascade = CascadeType.ALL)
	private RestaurantAddress address;

}
