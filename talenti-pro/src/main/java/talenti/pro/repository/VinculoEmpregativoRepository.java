package talenti.pro.repository;

import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.Query;
import talenti.pro.model.VinculoEmpregativo;

@Dependent
public class VinculoEmpregativoRepository extends GenericRepositoryImpl<VinculoEmpregativo> {

	public VinculoEmpregativoRepository() {
		super(VinculoEmpregativo.class);
	}

	public List<VinculoEmpregativo> listar() {
		return em.createQuery("select f FROM VinculoEmpregativo f", VinculoEmpregativo.class).getResultList();
	}

	public List<VinculoEmpregativo> buscar(String nome) {
		String jpql = "SELECT p FROM VinculoEmpregativo p WHERE 1=1";

		if (nome != null && !nome.trim().isEmpty()) {
			jpql += " AND LOWER(p.nome) LIKE LOWER(:nome)";
		}

		var query = em.createQuery(jpql, VinculoEmpregativo.class);
		if (nome != null && !nome.trim().isEmpty()) {
			query.setParameter("nome", "%" + nome + "%");
		}

		return query.getResultList();
	}

	@Override
	public List<VinculoEmpregativo> listarPaginado(int first, int pageSize, Map<String, Object> filters) {
		StringBuilder sql = new StringBuilder("SELECT * FROM vinculo_empregativo WHERE 1=1");

		if (filters != null) {
			if (filters.containsKey("nome") && filters.get("nome") != null
					&& !filters.get("nome").toString().isEmpty()) {
				sql.append(" AND LOWER(nome) LIKE LOWER(:nome)");
			}

		}

		sql.append(" ORDER BY id DESC");

		sql.append(" LIMIT :limit OFFSET :offset");

		Query query = em.createNativeQuery(sql.toString(), VinculoEmpregativo.class);

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
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM vinculo_empregativo WHERE 1=1");

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
