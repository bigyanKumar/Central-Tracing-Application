package com.payment.demo.globalexceptionhandler;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorResponse {
	
	private LocalDateTime timestamp; 
	private String message;
	private String details;

}
