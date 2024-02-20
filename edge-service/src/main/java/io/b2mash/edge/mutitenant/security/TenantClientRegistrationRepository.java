package io.b2mash.edge.mutitenant.security;

import io.b2mash.edge.mutitenant.tenantdetails.TenantDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class TenantClientRegistrationRepository implements ReactiveClientRegistrationRepository {

    private static final Map<String, Mono<ClientRegistration>> clientRegistrations = new ConcurrentHashMap<>();
    private final TenantDetailsService detailsService;

    @Override
    public Mono<ClientRegistration> findByRegistrationId(String registrationId) {
        return clientRegistrations.computeIfAbsent(registrationId, this::buildClientRegistration);
    }

    private Mono<ClientRegistration> buildClientRegistration(String registrationId) {
        var tenantDetails = detailsService.loadTenantByIdentifier(registrationId);
        return Mono.just(ClientRegistrations.fromOidcIssuerLocation(tenantDetails.issuer())
                .registrationId(registrationId)
                .clientId(tenantDetails.clientId())
                .clientSecret(tenantDetails.clientSecret())
                .redirectUri("{baseUrl}/login/oauth2/code/" + registrationId)
                .scope("openid")
                .build());
    }
}
