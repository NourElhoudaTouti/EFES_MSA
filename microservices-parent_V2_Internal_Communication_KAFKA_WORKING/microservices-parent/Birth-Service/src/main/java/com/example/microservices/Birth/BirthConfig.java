package com.example.microservices.Birth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BirthConfig {
        @Bean
        public RestTemplate restTemplate(){
            return new RestTemplate();
        }
    }

