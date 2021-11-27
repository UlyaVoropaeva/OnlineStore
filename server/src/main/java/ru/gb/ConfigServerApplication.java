package ru.gb;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableAutoConfiguration
@EnableEurekaServer
@EnableZuulServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(org.springframework.cloud.config.server.ConfigServerApplication.class).properties("spring.config.name=configserver")
                .run(args);

    }
}
