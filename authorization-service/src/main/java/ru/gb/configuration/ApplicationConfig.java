package ru.gb.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.gb.configuration.ApplicationClientsProperties;


@Configuration
@Import(ApplicationClientsProperties.class)
public class ApplicationConfig {
}
