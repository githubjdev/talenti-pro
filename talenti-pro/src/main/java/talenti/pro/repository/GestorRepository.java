package talenti.pro.repository;

import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.Query;
import talenti.pro.model.Gestor;

@Dependent
public class GestorRepository extends GenericRepositoryImpl<Gestor> {

	public GestorRepository() {
		super(Gestor.class);
	}

	public List<Gestor> listar() {
		return em.createQuery("select f FROM Gestor f", Gestor.class).getResultList();
	}

	public List<Gestor> buscar(String nome, String cpf) {
		String jpql = "SELECT p FROM Gestor p WHERE 1=1";

		if (nome != null && !nome.trim().isEmpty()) {
			jpql += " AND LOWER(p.nome) LIKE LOWER(:nome)";
		}

		var query = em.createQuery(jpql, Gestor.class);
		if (nome != null && !nome.trim().isEmpty()) {
			query.setParameter("nome", "%" + nome + "%");
		}

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gestor> listarPaginado(int first, int pageSize, Map<String, Object> filters) {
		StringBuilder sql = new StringBuilder("SELECT * FROM Gestor WHERE 1=1");

		if (filters != null) {
			if (filters.containsKey("nome") && filters.get("nome") != null
					&& !filters.get("nome").toString().isEmpty()) {
				sql.append(" AND LOWER(nome) LIKE LOWER(:nome)");
			}

		}

		sql.append(" ORDER BY id DESC");

		sql.append(" LIMIT :limit OFFSET :offset");

		Query query = em.createNativeQuery(sql.toString(), Gestor.class);

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
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM gestor WHERE 1=1");

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
