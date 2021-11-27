package ru.gb;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient
@SpringBootApplication
@EnableZuulProxy
public class AuthorizationClientApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AuthorizationClientApplication.class).properties("spring.config.name=web-authorization-service")
                .run(args);
        }

}
