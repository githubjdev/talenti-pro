package talenti.pro.repository;

import jakarta.enterprise.context.Dependent;
import talenti.pro.model.VersionadorBanco;

@Dependent
public class VersionadorBancoRepository extends GenericRepositoryImpl<VersionadorBanco> {

	public VersionadorBancoRepository() {
		super(VersionadorBanco.class);
	}

	public boolean existeTabela() {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT EXISTS ( SELECT 1 FROM information_schema.tables WHERE table_name = '"+getTableName()+"' )");
		
		return (boolean) em.createNativeQuery(sql.toString()).getSingleResult();

	}

	public boolean arquivoJaRodado(String file) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) > 0 as rodado from "+getTableName()+" where arquivosql = '" + file + "'");
		
		return (Boolean) em
				.createNativeQuery(sql.toString()).getSingleResult();
	}

}
