package talenti.pro.repository;

import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.Query;
import talenti.pro.model.Pergunta;

@Dependent
public class PerguntaRepository extends GenericRepositoryImpl<Pergunta> {

	public PerguntaRepository() {
		super(Pergunta.class);
	}

	public List<Pergunta> listar() {
		return em.createQuery("select f FROM Pergunta f", Pergunta.class).getResultList();
	}

	@Override
	public List<Pergunta> listarPaginado(int first, int pageSize, Map<String, Object> filters) {
		StringBuilder sql = new StringBuilder("SELECT * FROM Pergunta WHERE 1=1");

		if (filters != null) {
			if (filters.containsKey("descricao") && filters.get("descricao") != null
					&& !filters.get("descricao").toString().isEmpty()) {
				sql.append(" AND LOWER(descricao) LIKE LOWER(:descricao)");
			}

		}

		sql.append(" ORDER BY id DESC");

		sql.append(" LIMIT :limit OFFSET :offset");

		Query query = em.createNativeQuery(sql.toString(), Pergunta.class);

		if (filters != null) {
			if (filters.containsKey("descricao") && filters.get("descricao") != null
					&& !filters.get("descricao").toString().isEmpty()) {
				query.setParameter("descricao", "%" + filters.get("descricao").toString() + "%");
			}

		}

		query.setParameter("limit", pageSize);
		query.setParameter("offset", first);

		return query.getResultList();
	}

	@Override
	public Long contar(Map<String, Object> filters) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM pergunta WHERE 1=1");

		if (filters != null) {
			if (filters.containsKey("descricao") && filters.get("descricao") != null
					&& !filters.get("descricao").toString().isEmpty()) {
				sql.append(" AND LOWER(descricao) LIKE LOWER(:descricao)");
			}

		}

		Query query = em.createNativeQuery(sql.toString());

		if (filters != null) {
			if (filters.containsKey("descricao") && filters.get("descricao") != null
					&& !filters.get("descricao").toString().isEmpty()) {
				query.setParameter("descricao", "%" + filters.get("descricao").toString() + "%");
			}

		}

		Object result = query.getSingleResult();
		return ((Number) result).longValue();
	}

}
