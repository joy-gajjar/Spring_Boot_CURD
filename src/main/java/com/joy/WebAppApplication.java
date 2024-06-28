package com.joy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.joy.dao.UserRepository;
import com.joy.entities.User;

@SpringBootApplication
@ComponentScan(basePackages = "com.joy.dao,"+"com.joy.controller")
public class WebAppApplication {

	public static void main(String[] args) {
	SpringApplication.run(WebAppApplication.class, args);
	
	
	
	
	}

}
