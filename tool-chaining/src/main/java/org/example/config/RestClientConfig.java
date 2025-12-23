package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient userRestClient(@Value("${api.user.base-url}") String baseUrl) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Bean
    public RestClient orderRestClient(@Value("${api.order.base-url}") String baseUrl) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Bean
    public RestClient transactionRestClient(@Value("${api.transaction.base-url}") String baseUrl) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                // You can add default headers, timeouts, etc., here if needed
                .build();
    }
}