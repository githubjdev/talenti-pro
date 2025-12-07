package talenti.pro.repository;

import jakarta.enterprise.context.Dependent;
import talenti.pro.model.VersionadorBanco;

@Dependent
public class VersionadorBancoRepository extends GenericRepositoryImpl<VersionadorBanco> {

	public VersionadorBancoRepository() {
		super(VersionadorBanco.class);
	}

	public boolean existeTabela() {
		return (boolean) em.createNativeQuery(
				"SELECT EXISTS ( SELECT 1 FROM information_schema.tables WHERE table_name = 'versionador_banco' )")
				.getSingleResult();

	}

	public boolean arquivoJaRodado(String file) {
		return (Boolean) em
				.createNativeQuery(
						"select count(1) > 0 as rodado from versionador_banco where arquivo_sql = '" + file + "'")
				.getSingleResult();
	}

}
