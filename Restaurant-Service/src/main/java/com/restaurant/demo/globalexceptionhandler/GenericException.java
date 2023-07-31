package com.restaurant.demo.globalexceptionhandler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GenericException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public GenericException (String msg){
		super(msg);
	}

}
