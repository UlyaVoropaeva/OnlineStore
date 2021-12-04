package ru.gb.security;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.security.channel.ChannelSecurityInterceptor;
import org.springframework.integration.security.channel.SecuredChannel;
import org.springframework.messaging.Message;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@EnableIntegration
public class SecuredDirectChannel {

    @Bean(name = "startDirectChannel")
    @SecuredChannel(interceptor = "channelSecurityInterceptor", sendAccess = { "ROLE_VIEWER", "jane" })
    public DirectChannel startDirectChannel() {
        return new DirectChannel();
    }

    @ServiceActivator(inputChannel = "startDirectChannel", outputChannel = "endDirectChannel")
    @PreAuthorize("hasRole('ROLE_LOGGER')")
    public Message<?> logMessage(Message<?> message) {
        Logger.getAnonymousLogger().info(message.toString());
        return message;
    }

    @Bean(name = "endDirectChannel")
    @SecuredChannel(interceptor = "channelSecurityInterceptor", sendAccess = { "ROLE_EDITOR" })
    public DirectChannel endDirectChannel() {
        return new DirectChannel();
    }
}