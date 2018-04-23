package com.osi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan("com.osi")
@PropertySources(value = { @PropertySource("classpath:/application.properties"),
		@PropertySource("classpath:/errorOrSuccessMsg.properties") })
public class RestservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestservicesApplication.class, args);
	}
}
