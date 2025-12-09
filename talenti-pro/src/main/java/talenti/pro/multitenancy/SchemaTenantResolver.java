package talenti.pro.multitenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class SchemaTenantResolver implements CurrentTenantIdentifierResolver {

	private static final String DEFAULT_TENANT = "public";

	@Override
	public String resolveCurrentTenantIdentifier() {
		String tenant = TenantContext.getTenantId();
		return (tenant != null) ? tenant : DEFAULT_TENANT;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
}
