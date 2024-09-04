package com.example.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
//@EnableEurekaClient
public class BirthApplication {

	public static void main(String[] args) {
		SpringApplication.run(BirthApplication.class, args);
	}

}
