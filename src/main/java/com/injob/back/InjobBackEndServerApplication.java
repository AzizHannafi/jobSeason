package com.injob.back;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.injob.back", "com.injob.back.mapper"})
@ConfigurationPropertiesScan
@EnableJpaRepositories(basePackages = "com.injob.back.repository")
public class InjobBackEndServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InjobBackEndServerApplication.class, args);
		System.out.println("Welcome to INJOB BACKEND SERVER \uD83D\uDE0E");
	}

}
