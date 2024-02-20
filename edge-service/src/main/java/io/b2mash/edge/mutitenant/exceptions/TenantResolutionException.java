package io.b2mash.edge.mutitenant.exceptions;

public class TenantResolutionException extends IllegalStateException {

    public TenantResolutionException() {
        super("Error when trying to resolve the current tenant");
    }

    public TenantResolutionException(String message) {
        super(message);
    }

}
