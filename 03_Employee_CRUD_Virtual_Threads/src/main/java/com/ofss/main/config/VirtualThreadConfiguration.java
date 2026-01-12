package com.ofss.main.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class VirtualThreadConfiguration {

	@Bean(name = "virtualThreadExecutor")
	public Executor virtualThreadExecutor() {
		log.info("virtualThreadExecutor object created");
		return Executors.newVirtualThreadPerTaskExecutor();
	}

}
