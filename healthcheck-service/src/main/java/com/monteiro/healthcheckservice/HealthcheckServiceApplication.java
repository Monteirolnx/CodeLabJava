package com.monteiro.healthcheckservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.monteiro.healthcheckservice")
public class HealthcheckServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(HealthcheckServiceApplication.class, args);
	}
}