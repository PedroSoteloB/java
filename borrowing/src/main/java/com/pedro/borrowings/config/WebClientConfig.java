// package com.pedro.borrowings.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.MediaType;
// import org.springframework.web.reactive.function.client.WebClient;

// @Configuration
// public class WebClientConfig {

//     @Bean(name = "bookWebClient")
//     public WebClient bookWebClient(WebClient.Builder builder) {
//         return builder
//                 .baseUrl("http://localhost:8082")
//                 .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                 .build();
//     }

//     @Bean(name = "userClient")
//     public WebClient userWebClient(WebClient.Builder builder) {
//         return builder
//                 .baseUrl("http://localhost:8081") 
//                 .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                 .build();
//     }
// }

// package com.pedro.borrowings.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.MediaType;
// import org.springframework.web.reactive.function.client.WebClient;

// @Configuration
// public class WebClientConfig {

//     @Bean(name = "bookWebClient")
//     public WebClient bookWebClient(WebClient.Builder builder) {
//         return builder
//                 .baseUrl("http://localhost:8082")
//                 .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                 .build();
//     }

//     @Bean(name = "userClient")
//     public WebClient userWebClient(WebClient.Builder builder) {
//         return builder
//                 .baseUrl("http://localhost:8081") 
//                 .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                 .build();
//     }
// }

package com.pedro.borrowings.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${book.service.url}")
    private String bookServiceUrl;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Bean(name = "bookWebClient")
    public WebClient bookWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl(bookServiceUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean(name = "userClient")
    public WebClient userWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl(userServiceUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}