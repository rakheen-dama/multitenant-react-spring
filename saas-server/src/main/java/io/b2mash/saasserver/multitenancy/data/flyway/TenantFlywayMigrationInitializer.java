package io.b2mash.saasserver.multitenancy.data.flyway;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class TenantFlywayMigrationInitializer implements InitializingBean, Ordered {

    private final DataSource dataSource;
    private final Flyway defaultFlyway;


    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
