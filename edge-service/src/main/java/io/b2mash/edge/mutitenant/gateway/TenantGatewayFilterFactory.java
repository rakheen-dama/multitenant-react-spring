package io.b2mash.edge.mutitenant.gateway;

import io.micrometer.common.KeyValue;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.reactive.ServerHttpObservationFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.core.publisher.Mono;

/**
 * Custom filter to extend the AddRequestHeader built-in filter so to
 * also include tenant information into the ObservabilityContext.
 */
@Component
@AllArgsConstructor
public class TenantGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                var tenantId = ServerWebExchangeUtils.expand(exchange, config.getValue());
                ServerHttpRequest request = addTenantToRequest(exchange, tenantId, config);
                addTenantToObservation(tenantId, exchange);
                return chain.filter(exchange.mutate().request(request).build());
            }
        };
    }

    private ServerHttpRequest addTenantToRequest(ServerWebExchange exchange, String tenantId, NameValueConfig config) {
        var tenantHeader = config.getName();
        return exchange.getRequest().mutate()
                .headers(httpHeaders -> httpHeaders.add(tenantHeader, tenantId))
                .build();
    }

    private void addTenantToObservation(String tenantId, ServerWebExchange exchange) {
        ServerHttpObservationFilter.findObservationContext(exchange).ifPresent(serverRequestObservationContext ->
                serverRequestObservationContext.addHighCardinalityKeyValue(KeyValue.of("tenant.id", tenantId)));
    }
}
