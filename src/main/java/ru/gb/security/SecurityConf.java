package ru.gb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.security.channel.ChannelSecurityInterceptor;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConf extends GlobalMethodSecurityConfiguration {

   @Bean
    public AuthenticationManager	authenticate () throws Exception {
       return super.authenticationManager();
   }

    @Bean
    public AccessDecisionManager customAccessDecisionMng() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<>();
        decisionVoters.add(new RoleVoter());
        decisionVoters.add(new UsernameAccessDecisionVoter());
        AccessDecisionManager manager = new AffirmativeBased(decisionVoters);
        return manager;
    }

    @Autowired
    @Bean
    public ChannelSecurityInterceptor chSecurityInterceptor(AuthenticationManager authenticationManager, AccessDecisionManager accessDecisionManager) {
        ChannelSecurityInterceptor chSecurityInterceptor = new ChannelSecurityInterceptor();
        chSecurityInterceptor.setAuthenticationManager(authenticationManager);
        chSecurityInterceptor.setAccessDecisionManager(accessDecisionManager);
        return chSecurityInterceptor;
    }
}