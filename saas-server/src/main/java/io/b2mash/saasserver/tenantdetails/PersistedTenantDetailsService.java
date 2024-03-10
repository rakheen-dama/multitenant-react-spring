package io.b2mash.saasserver.tenantdetails;

import io.b2mash.edge.mutitenant.exceptions.TenantResolutionException;
import io.b2mash.edge.mutitenant.tenantdetails.persistence.TenantConfig;
import io.b2mash.edge.mutitenant.tenantdetails.persistence.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersistedTenantDetailsService implements TenantDetailsService{

    private final TenantRepository tenantRepository;

    @Override
    public List<TenantDetails> loadAllTenants() {
        return tenantRepository.findAll().stream().map(
                PersistedTenantDetailsService::getTenantDetails).collect(Collectors.toList());
    }

    @Override
    public TenantDetails loadTenantByIdentifier(String identifier) {
        return getTenantDetails(tenantRepository.findByIdentifier(identifier)
                .orElseThrow(() -> new TenantResolutionException("A valid tenant must be specified for authentication requests")));
    }

    private static TenantDetails getTenantDetails(TenantConfig v) {
        return new TenantDetails(v.getIdentifier(), v.isEnabled(), v.getClientId(),
                v.getClientSecret(), v.getIssuer()
        );
    }
}
