package io.b2mash.edge.mutitenant.tenantdetails;

public record TenantDetails(String identifier, boolean enabled, String clientId,
                            String clientSecret, String issuer) {
}
