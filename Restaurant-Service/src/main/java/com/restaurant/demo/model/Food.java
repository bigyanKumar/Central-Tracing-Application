package com.restaurant.demo.model;

import javax.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Food {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer foodId;
	private String name;
	private Double price;
	@ManyToOne(targetEntity = Restaurant.class,cascade =CascadeType.ALL)
	@JoinColumn(name="restaurantId")
	private Restaurant restaurant;
	

}
