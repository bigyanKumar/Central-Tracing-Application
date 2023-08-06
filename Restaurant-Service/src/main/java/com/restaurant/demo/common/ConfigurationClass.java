package com.restaurant.demo.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;

import brave.sampler.Sampler;
import nonapi.io.github.classgraph.json.JSONSerializer;

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
//	@Bean
//	public Map<String,Object> producerConfig(){
//		Map<String,Object> props= new HashMap<>();
//		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JSONSerializer.class);
//		return props;
//	}
//	@Bean
//	public ProducerFactory<String, Object> producerFactory(){
//		return new DefaultKafkaProducerFactory<>(producerConfig());
//	}
//	
//	@Bean
//	public KafkaTemplate<String, Object> kafkaTemplate(){
//		return new KafkaTemplate<>(producerFactory());
//	}
	
}
