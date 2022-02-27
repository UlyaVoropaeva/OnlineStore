
package ru.gb.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Data
@Component
@ConfigurationProperties("appclients")
public class ApplicationClientsProperties {

    private List<ApplicationClient> clients = new ArrayList<>();

    @Data
    public static class ApplicationClient {
        private String username;
        private String password;
        private String[] roles;
    }

}
