package io.b2mash.edge.config;

import io.b2mash.edge.mutitenant.security.TenantAuthenticationEntryPoint;
import io.b2mash.edge.mutitenant.security.TenantClientRegistrationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, TenantClientRegistrationRepository clientRegistrationRepository) {
        return http
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/actuator/**", "/tenant-login/**", "/test").permitAll()
                        .anyExchange().authenticated())
                .oauth2Login(oauth2 -> oauth2
                        .clientRegistrationRepository(clientRegistrationRepository))
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(new TenantAuthenticationEntryPoint()))
                .build();
    }
}
