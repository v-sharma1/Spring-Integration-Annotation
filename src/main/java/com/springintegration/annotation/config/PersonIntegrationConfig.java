package com.springintegration.annotation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class PersonIntegrationConfig {

	@Bean
	public MessageChannel findByIdRequest() {
		DirectChannel dc = new DirectChannel();
		return dc;
	}
	
	@Bean
	public MessageChannel findByIdResponse() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel createRequest() {
		return new DirectChannel();
	}
	
	@Bean
	public MessageChannel createMQRequest() {
		return new DirectChannel();
	}
	
}
