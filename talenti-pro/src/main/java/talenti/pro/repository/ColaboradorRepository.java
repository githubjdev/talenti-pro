package talenti.pro.repository;

import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.Query;
import talenti.pro.model.Colaborador;

@Dependent
public class ColaboradorRepository extends GenericRepositoryImpl<Colaborador> {

	public ColaboradorRepository() {
		super(Colaborador.class);
	}

	public List<Colaborador> listar() {
		return em.createQuery("select f FROM Colaborador f", Colaborador.class).getResultList();
	}

	public List<Colaborador> buscar(String nome, String cpf) {
		String jpql = "SELECT p FROM Colaborador p WHERE 1=1";

		if (nome != null && !nome.trim().isEmpty()) {
			jpql += " AND LOWER(p.nome) LIKE LOWER(:nome)";
		}
		
		if (cpf != null && !cpf.trim().isEmpty()) {
			jpql += " AND LOWER(p.cpf) LIKE LOWER(:cpf)";
		}

		var query = em.createQuery(jpql, Colaborador.class);
		if (nome != null && !nome.trim().isEmpty()) {
			query.setParameter("nome", "%" + nome + "%");
		}
		
		if (cpf != null && !cpf.trim().isEmpty()) {
			query.setParameter("cpf", "%" + cpf + "%");
		}

		return query.getResultList();
	}

	@Override
	public List<Colaborador> listarPaginado(int first, int pageSize, Map<String, Object> filters) {
		StringBuilder sql = new StringBuilder("SELECT * FROM Colaborador WHERE 1=1");

		if (filters != null) {
			if (filters.containsKey("nome") && filters.get("nome") != null
					&& !filters.get("nome").toString().isEmpty()) {
				sql.append(" AND LOWER(nome) LIKE LOWER(:nome)");
			}

		}

		sql.append(" ORDER BY id DESC");

		sql.append(" LIMIT :limit OFFSET :offset");

		Query query = em.createNativeQuery(sql.toString(), Colaborador.class);

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
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM colaborador WHERE 1=1");

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
