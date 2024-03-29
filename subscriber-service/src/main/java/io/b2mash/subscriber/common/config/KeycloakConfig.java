package io.b2mash.subscriber.common.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    @Bean
    public Keycloak keycloakMaster() {

        return KeycloakBuilder.builder()
                .serverUrl("")
                .realm("master")
                .clientId("admin-cli")
                .grantType(OAuth2Constants.PASSWORD)
                .username("")
                .password("")
                .build();
    }
}
