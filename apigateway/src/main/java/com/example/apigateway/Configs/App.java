package com.example.apigateway.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class App {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
