package com.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserNotificationDto {
	
	private Integer userId;
	private Integer foodId;
	private String userName;
	private String userEmail;
	private String userMobile;

}
