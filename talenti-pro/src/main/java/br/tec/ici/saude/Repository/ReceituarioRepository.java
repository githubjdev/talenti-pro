package br.tec.ici.saude.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.tec.ici.saude.Dto.MedicamentoMaisPrescritoDTO;
import br.tec.ici.saude.model.Receituario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@SuppressWarnings("unchecked")
@ApplicationScoped
public class ReceituarioRepository extends GenericRepositoryImpl<Receituario> {

	@Inject
	private EntityManager em;

	public ReceituarioRepository() {
		super(Receituario.class);
	}

	@Override
	public void salvar(Receituario Receituario) {
		em.getTransaction().begin();
		if (Receituario.getId() == null) {
			em.persist(Receituario);
		} else {
			em.merge(Receituario);
		}

		em.getTransaction().commit();
	}

	@Override
	public void excluir(Long id) {
		Receituario receituario = em.find(Receituario.class, id);
		em.getTransaction().begin();
		if (receituario != null) {
			em.remove(receituario);
		}
		em.getTransaction().commit();
	}

	public List<Receituario> listar() {
		return em.createQuery("FROM Receituario", Receituario.class).getResultList();
	}

	@Override
	public Receituario buscarPorId(Long id) {
		return em.find(Receituario.class, id);
	}

	public List<Receituario> buscar(String nome, String cpf) {
		String jpql = "SELECT p FROM Receituario p WHERE 1=1";

		if (nome != null && !nome.trim().isEmpty()) {
			jpql += " AND LOWER(p.nome) LIKE LOWER(:nome)";
		}

		var query = em.createQuery(jpql, Receituario.class);
		if (nome != null && !nome.trim().isEmpty()) {
			query.setParameter("nome", "%" + nome + "%");
		}

		return query.getResultList();
	}

	@Override
	public List<Receituario> listarPaginado(int first, int pageSize, Map<String, Object> filters) {

		if (filters != null) {
			if (filters.containsKey("idmedicamento") && filters.get("idmedicamento") != null
					&& !filters.get("idmedicamento").toString().isEmpty()) {
				return this.listarPaginadoMedicamento(first, pageSize, filters);
			}
		}

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT r.id, r.id_paciente ");
		sql.append("FROM receituario r ");

		sql.append("INNER JOIN paciente p ON p.id = r.id_paciente ");
		sql.append("WHERE 1=1 ");

		if (filters != null) {
			if (filters.containsKey("idpaciente") && filters.get("idpaciente") != null
					&& !filters.get("idpaciente").toString().isEmpty()) {
				sql.append(" AND p.id = :idpaciente ");
			}

			if (filters.containsKey("nome") && filters.get("nome") != null
					&& !filters.get("nome").toString().isEmpty()) {
				sql.append(" AND LOWER(p.nome) LIKE LOWER(:nome) ");
			}

			if (filters.containsKey("cpf") && filters.get("cpf") != null && !filters.get("cpf").toString().isEmpty()) {
				sql.append(" AND p.cpf LIKE :cpf ");
			}
		}

		sql.append(" ORDER BY r.id DESC ");
		sql.append(" LIMIT :limit OFFSET :offset ");

		Query query = em.createNativeQuery(sql.toString(), Receituario.class);

		if (filters != null) {
			if (filters.containsKey("idpaciente") && filters.get("idpaciente") != null
					&& !filters.get("idpaciente").toString().isEmpty()) {
				query.setParameter("idpaciente", Long.parseLong(filters.get("idpaciente").toString()));
			}

			if (filters.containsKey("nome") && filters.get("nome") != null
					&& !filters.get("nome").toString().isEmpty()) {
				query.setParameter("nome", "%" + filters.get("nome") + "%");
			}

			if (filters.containsKey("cpf") && filters.get("cpf") != null && !filters.get("cpf").toString().isEmpty()) {
				query.setParameter("cpf", "%" + filters.get("cpf") + "%");
			}
		}

		query.setParameter("limit", pageSize);
		query.setParameter("offset", first);

		return query.getResultList();
	}

	public List<Receituario> listarPaginadoMedicamento(int first, int pageSize, Map<String, Object> filters) {
		StringBuilder sql = new StringBuilder();
		sql.append("select r.id, r.id_paciente from medicamento_receita as mr ");
		sql.append("inner join receituario as r  on r.id = mr.id_receituario ");

		sql.append("where mr.id_medicamento = :idmedicamento");

		sql.append(" ORDER BY r.id DESC ");
		sql.append(" LIMIT :limit OFFSET :offset ");

		Query query = em.createNativeQuery(sql.toString(), Receituario.class);
		query.setParameter("idmedicamento", filters.get("idmedicamento"));

		query.setParameter("limit", pageSize);
		query.setParameter("offset", first);

		return query.getResultList();
	}

	@Override
	public Long contar(Map<String, Object> filters) {

		if (filters != null) {
			if (filters.containsKey("idmedicamento") && filters.get("idmedicamento") != null
					&& !filters.get("idmedicamento").toString().isEmpty()) {
				return this.contarMedicamento(filters);
			}
		}

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) ");
		sql.append("FROM receituario r ");
		sql.append("INNER JOIN paciente p ON p.id = r.id_paciente ");
		sql.append("WHERE 1=1 ");

		if (filters != null) {
			if (filters.containsKey("idpaciente") && filters.get("idpaciente") != null
					&& !filters.get("idpaciente").toString().isEmpty()) {
				sql.append(" AND p.id = :idpaciente ");
			}

			if (filters.containsKey("nome") && filters.get("nome") != null
					&& !filters.get("nome").toString().isEmpty()) {
				sql.append(" AND LOWER(p.nome) LIKE LOWER(:nome) ");
			}

			if (filters.containsKey("cpf") && filters.get("cpf") != null && !filters.get("cpf").toString().isEmpty()) {
				sql.append(" AND p.cpf LIKE :cpf ");
			}
		}

		Query query = em.createNativeQuery(sql.toString());

		if (filters != null) {
			if (filters.containsKey("idpaciente") && filters.get("idpaciente") != null
					&& !filters.get("idpaciente").toString().isEmpty()) {
				query.setParameter("idpaciente", Long.parseLong(filters.get("idpaciente").toString()));
			}

			if (filters.containsKey("nome") && filters.get("nome") != null
					&& !filters.get("nome").toString().isEmpty()) {
				query.setParameter("nome", "%" + filters.get("nome") + "%");
			}

			if (filters.containsKey("cpf") && filters.get("cpf") != null && !filters.get("cpf").toString().isEmpty()) {
				query.setParameter("cpf", "%" + filters.get("cpf") + "%");
			}
		}

		Object result = query.getSingleResult();
		return ((Number) result).longValue();
	}

	public Long contarMedicamento(Map<String, Object> filters) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) ");
		sql.append("FROM medicamento_receita mr ");
		sql.append("INNER JOIN receituario r ON r.id = mr.id_receituario ");
		sql.append("WHERE mr.id_medicamento = :idmedicamento");

		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idmedicamento", filters.get("idmedicamento"));

		Object result = query.getSingleResult();
		return ((Number) result).longValue();
	}

	public List<MedicamentoMaisPrescritoDTO> medicamentoMaisPrescritos() {
		String sql = """
				SELECT
				    m.id,
				    m.nome,
				    COUNT(mr.id_medicamento) AS totalPrescricoes
				FROM medicamento_receita mr
				INNER JOIN medicamento m ON m.id = mr.id_medicamento
				GROUP BY m.id, m.nome
				ORDER BY totalPrescricoes DESC
				LIMIT 2;
				  """;

		Query query = em.createNativeQuery(sql);
		List<Object[]> rows = query.getResultList();

		List<MedicamentoMaisPrescritoDTO> resultado = new ArrayList<>();
		for (Object[] r : rows) {
			resultado.add(new MedicamentoMaisPrescritoDTO(((Number) r[0]).longValue(), (String) r[1],
					((Number) r[2]).longValue()));
		}

		return resultado;
	}

}
