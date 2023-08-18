package com.example.backendpre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.backendpre.question.controller", "com.example.backendpre.question.mapper"})
public class BackendPreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendPreApplication.class, args);
	}

}
