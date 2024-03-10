package io.b2mash.edge.mutitenant.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.DefaultServerRedirectStrategy;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.ServerRedirectStrategy;
import org.springframework.security.web.server.savedrequest.ServerRequestCache;
import org.springframework.security.web.server.savedrequest.WebSessionServerRequestCache;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class TenantAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    private ServerRedirectStrategy redirectStrategy = new DefaultServerRedirectStrategy();
    private ServerRequestCache serverRequestCache = new WebSessionServerRequestCache();

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        String baseLoginUri = "/oauth2/authorization/";
        String tenantId = exchange.getRequest().getURI().getHost().split("\\.")[0];
        URI tenantLoginLocation = URI.create(baseLoginUri + tenantId);
        return this.serverRequestCache.saveRequest(exchange)
                .then(this.redirectStrategy.sendRedirect(exchange, tenantLoginLocation));
    }

    public void setRequestCache(ServerRequestCache serverRequestCache) {
        Assert.notNull(serverRequestCache, "serverRequestCache cannot be null");
        this.serverRequestCache = serverRequestCache;
    }

    public void setRedirectStrategy(ServerRedirectStrategy redirectStrategy) {
        Assert.notNull(redirectStrategy, "redirectStrategy cannot be null");
        this.redirectStrategy = redirectStrategy;
    }
}
