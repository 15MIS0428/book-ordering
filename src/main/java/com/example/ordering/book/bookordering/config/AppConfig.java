package com.example.ordering.book.bookordering.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean
    public WebClient getWebclient(WebClient.Builder builder){
        return builder.baseUrl("http://localhost:8080")   // URL for book-inventory application
                .build();
    }


}
