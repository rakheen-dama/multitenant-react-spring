package io.b2mash.saasserver.tenantdetails;

/**
 * Provides core tenant information.
 */
public record TenantDetails(
        String identifier,
        boolean enabled,
        String schema,
        String issuer
) {}
