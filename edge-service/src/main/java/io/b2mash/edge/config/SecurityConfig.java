package io.b2mash.edge.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity, )
}
