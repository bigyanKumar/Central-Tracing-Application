package com.notification.demo.common;

import org.springframework.web.client.RestTemplate;

public class BaseService {
	
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
