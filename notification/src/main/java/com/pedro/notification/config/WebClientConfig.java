package com.pedro.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${borrowing.service.url}")
    private String borrowingServiceUrl;

    @Bean
    public WebClient borrowingWebClient() {
        return WebClient.builder()
                .baseUrl(borrowingServiceUrl)
                .build();
    }
}
