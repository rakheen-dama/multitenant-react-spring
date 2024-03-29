package io.b2mash.edge.mutitenant.tenantdetails.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TenantConfig {

    @Id
    private long id;

    private String identifier;
    private boolean enabled;
    private String clientId;
    private String clientSecret;
    private String issuer;
    private Instant createdDate;
    private Instant lastModifiedDate;
}
