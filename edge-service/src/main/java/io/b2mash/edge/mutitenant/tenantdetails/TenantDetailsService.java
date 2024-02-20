package io.b2mash.edge.mutitenant.tenantdetails;

import java.util.List;

public interface TenantDetailsService {

    List<TenantDetails> loadAllTenants();

    TenantDetails loadTenantByIdentifier(String identifier);
}
