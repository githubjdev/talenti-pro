package talenti.pro.repository.cadastro;

import java.util.List;
import java.util.Map;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.Query;
import talenti.pro.exceptionhandler.ValidacaoException;
import talenti.pro.model.cadastro.Prefeitura;
import talenti.pro.repository.GenericRepositoryImpl;

@RolesAllowed({"ROLE_MASTER"})
@Dependent
public class PrefeituraRepository extends GenericRepositoryImpl<Prefeitura> {

	public PrefeituraRepository() {
		super(Prefeitura.class);
	}
	

	public String shemaBanco(String nomeSchema) {
		Query query = em.createNativeQuery("select schema_name from information_schema.schemata where schema_name = :nome");
		query.setParameter("nome", nomeSchema);
		query.setMaxResults(1);
		return query.getResultStream().findFirst().orElse("").toString();
	}
	
	public boolean existeSchema(String schema) {
		return !this.shemaBanco(schema).isEmpty();
	}
	
	public void criarSchemaBd(String nomeSchema) {
		
		nomeSchema = nomeSchema.toLowerCase().trim();
		
		if (!nomeSchema.matches("[a-z0-9_]+")) {
			throw new ValidacaoException("Nome de schema inválido");
		} else {
			Query query = em.createNativeQuery("CREATE SCHEMA IF NOT EXISTS " + nomeSchema);
			query.executeUpdate();
		}
	}
	

	public List<Prefeitura> listar() {
		return em.createQuery("select f FROM "+getEntityName()+" f", Prefeitura.class).getResultList();
	}

	public List<Prefeitura> buscar(String nome) {
		String jpql = "SELECT p FROM "+getEntityName()+" p WHERE 1=1";

		if (nome != null && !nome.trim().isEmpty()) {
			jpql += " AND LOWER(p.nome) LIKE LOWER(:nome)";
		}

		var query = em.createQuery(jpql, Prefeitura.class);
		if (nome != null && !nome.trim().isEmpty()) {
			query.setParameter("nome", "%" + nome + "%");
		}

		return query.getResultList();
	}

	@Override
	public List<Prefeitura> listarPaginado(int first, int pageSize, Map<String, Object> filters) {
		StringBuilder sql = new StringBuilder("SELECT * FROM "+getTableName()+" WHERE 1=1");

		if (filters != null) {
			if (filters.containsKey("nome") && filters.get("nome") != null
					&& !filters.get("nome").toString().isEmpty()) {
				sql.append(" AND LOWER(nome) LIKE LOWER(:nome)");
			}

		}

		sql.append(" ORDER BY id DESC");

		sql.append(" LIMIT :limit OFFSET :offset");

		Query query = em.createNativeQuery(sql.toString(), Prefeitura.class);

		if (filters != null) {
			if (filters.containsKey("nome") && filters.get("nome") != null
					&& !filters.get("nome").toString().isEmpty()) {
				query.setParameter("nome", "%" + filters.get("nome").toString() + "%");
			}

		}

		query.setParameter("limit", pageSize);
		query.setParameter("offset", first);

		return query.getResultList();
	}

	@Override
	public Long contar(Map<String, Object> filters) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM "+getTableName()+" WHERE 1=1");

		if (filters != null) {
			if (filters.containsKey("nome") && filters.get("nome") != null
					&& !filters.get("nome").toString().isEmpty()) {
				sql.append(" AND LOWER(nome) LIKE LOWER(:nome)");
			}

		}

		Query query = em.createNativeQuery(sql.toString());

		if (filters != null) {
			if (filters.containsKey("nome") && filters.get("nome") != null
					&& !filters.get("nome").toString().isEmpty()) {
				query.setParameter("nome", "%" + filters.get("nome").toString() + "%");
			}

		}

		Object result = query.getSingleResult();
		return ((Number) result).longValue();
	}

}
