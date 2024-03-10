package io.b2mash.edge.mutitenant;

import io.b2mash.edge.mutitenant.tenantdetails.TenantDetails;
import io.b2mash.edge.mutitenant.tenantdetails.TenantDetailsService;
import io.b2mash.edge.mutitenant.tenantdetails.persistence.TenantConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TController {

    private final TenantDetailsService detailsService;

    @GetMapping("/test")
    public List<TenantDetails> getConfig() {
        return detailsService.loadAllTenants();
    }
}
