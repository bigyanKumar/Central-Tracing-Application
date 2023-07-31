package com.restaurant.demo.common;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import brave.sampler.Sampler;

@Configuration
public class ConfigurationClass {
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public Sampler alwaysSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
	
	@Bean
	public NewTopic createTopicforNotification() {
		return new NewTopic("notification",4,(short)1);
	}
	@Bean
	public NewTopic createTopicforDashboard() {
		return new NewTopic("dashboard",3,(short)1);
	}
}
