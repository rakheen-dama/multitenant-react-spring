package io.b2mash.saasserver.tenantdetails.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TenantRepository extends JpaRepository<TenantConfig, Long> {

    Optional<TenantConfig> findByIdentifier(String identifier);
}
