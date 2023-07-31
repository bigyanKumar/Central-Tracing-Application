package com.dashboard.demo.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer userId;
	private String name;
	private String mobile;
	private String Email;

}
