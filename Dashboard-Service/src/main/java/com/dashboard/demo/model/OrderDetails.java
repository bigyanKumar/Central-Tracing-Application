package com.dashboard.demo.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	private LocalDateTime  orderedAt;
	private LocalDateTime ordereDone;
	@OneToOne(cascade = CascadeType.ALL)
	private FoodDetails foodDetails;
	@OneToOne(cascade = CascadeType.ALL)
	private UserDetails userDetails;
	@Enumerated(EnumType.STRING)
	private Status  status;

}
