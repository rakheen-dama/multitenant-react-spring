package io.b2mash.saasserver.multitenancy.data.cache;

import io.b2mash.saasserver.multitenancy.context.TenantContextHolder;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * An implementation of {@link KeyGenerator} that generates cache keys combining the
 * current tenant identifier with the given method and parameters.
 */
@Component
public class TenantKeyGenerator implements KeyGenerator {

    @Override
    @NonNull
    public Object generate(Object target, Method method, Object... params) {
        return SimpleKeyGenerator.generateKey(TenantContextHolder.getRequiredTenantIdentifier(),
                params);
    }
}
