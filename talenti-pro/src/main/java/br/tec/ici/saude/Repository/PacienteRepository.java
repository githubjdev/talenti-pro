package br.tec.ici.saude.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.tec.ici.saude.Dto.MedicamentoMaisPrescritoDTO;
import br.tec.ici.saude.Dto.PacienteMaisMedicamentosDTO;
import br.tec.ici.saude.model.Paciente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@SuppressWarnings("unchecked")
@ApplicationScoped
public class PacienteRepository extends GenericRepositoryImpl<Paciente> {

	@Inject
	private EntityManager em;

	public PacienteRepository() {
		super(Paciente.class);
	}

	@Override
	public void salvar(Paciente paciente) {
		em.getTransaction().begin();
		if (paciente.getId() == null) {
			em.persist(paciente);
		} else {
			em.merge(paciente);
		}

		em.getTransaction().commit();
	}

	@Override
	public void excluir(Long id) {
		Paciente paciente = em.find(Paciente.class, id);
		em.getTransaction().begin();
		if (paciente != null) {
			em.remove(paciente);
		}
		em.getTransaction().commit();
	}

	public List<Paciente> listar() {
		return em.createQuery("FROM Paciente", Paciente.class).getResultList();
	}

	@Override
	public Paciente buscarPorId(Long id) {
		return em.find(Paciente.class, id);
	}

	public List<Paciente> buscar(String nome, String cpf) {
		String jpql = "SELECT p FROM Paciente p WHERE 1=1";

		if (nome != null && !nome.trim().isEmpty()) {
			jpql += " AND LOWER(p.nome) LIKE LOWER(:nome)";
		}
		if (cpf != null && !cpf.trim().isEmpty()) {
			jpql += " AND p.cpf LIKE :cpf";
		}

		var query = em.createQuery(jpql, Paciente.class);
		if (nome != null && !nome.trim().isEmpty()) {
			query.setParameter("nome", "%" + nome + "%");
		}
		if (cpf != null && !cpf.trim().isEmpty()) {
			query.setParameter("cpf", "%" + cpf + "%");
		}

		return query.getResultList();
	}

	@Override
	public List<Paciente> listarPaginado(int first, int pageSize, Map<String, Object> filters) {
		StringBuilder sql = new StringBuilder("SELECT * FROM paciente WHERE 1=1");

		if (filters != null) {
			if (filters.containsKey("nome") && filters.get("nome") != null
					&& !filters.get("nome").toString().isEmpty()) {
				sql.append(" AND LOWER(nome) LIKE LOWER(:nome)");
			}
			if (filters.containsKey("cpf") && filters.get("cpf") != null && !filters.get("cpf").toString().isEmpty()) {
				sql.append(" AND cpf LIKE :cpf");
			}
		}

		sql.append(" ORDER BY id DESC");

		sql.append(" LIMIT :limit OFFSET :offset");

		Query query = em.createNativeQuery(sql.toString(), Paciente.class);

		if (filters != null) {
			if (filters.containsKey("nome") && filters.get("nome") != null
					&& !filters.get("nome").toString().isEmpty()) {
				query.setParameter("nome", "%" + filters.get("nome").toString() + "%");
			}
			if (filters.containsKey("cpf") && filters.get("cpf") != null && !filters.get("cpf").toString().isEmpty()) {
				query.setParameter("cpf", "%" + filters.get("cpf").toString() + "%");
			}
		}

		query.setParameter("limit", pageSize);
		query.setParameter("offset", first);

		return query.getResultList();
	}

	@Override
	public Long contar(Map<String, Object> filters) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM paciente WHERE 1=1");

		if (filters != null) {
			if (filters.containsKey("nome") && filters.get("nome") != null
					&& !filters.get("nome").toString().isEmpty()) {
				sql.append(" AND LOWER(nome) LIKE LOWER(:nome)");
			}
			if (filters.containsKey("cpf") && filters.get("cpf") != null && !filters.get("cpf").toString().isEmpty()) {
				sql.append(" AND cpf LIKE :cpf");
			}
		}

		Query query = em.createNativeQuery(sql.toString());

		if (filters != null) {
			if (filters.containsKey("nome") && filters.get("nome") != null
					&& !filters.get("nome").toString().isEmpty()) {
				query.setParameter("nome", "%" + filters.get("nome").toString() + "%");
			}
			if (filters.containsKey("cpf") && filters.get("cpf") != null && !filters.get("cpf").toString().isEmpty()) {
				query.setParameter("cpf", "%" + filters.get("cpf").toString() + "%");
			}
		}

		Object result = query.getSingleResult();
		return ((Number) result).longValue();
	}

	public List<PacienteMaisMedicamentosDTO> buscarTopPacientesMaisMedicados() {
		String sql = """
				    SELECT
				        p.id AS idPaciente,
				        p.nome AS nomePaciente,
				        COUNT(mr.id_medicamento) AS totalMedicamentos
				    FROM paciente p
				    INNER JOIN receituario r ON r.id_paciente = p.id
				    INNER JOIN medicamento_receita mr ON mr.id_receituario = r.id
				    GROUP BY p.id, p.nome
				    ORDER BY totalMedicamentos DESC
				    LIMIT 2
				""";

		Query query = em.createNativeQuery(sql);
		List<Object[]> rows = query.getResultList();

		List<PacienteMaisMedicamentosDTO> resultado = new ArrayList<>();
		for (Object[] r : rows) {
			resultado.add(new PacienteMaisMedicamentosDTO(((Number) r[0]).longValue(), (String) r[1],
					((Number) r[2]).longValue()));
		}

		return resultado;
	}

	public List<MedicamentoMaisPrescritoDTO> listarPacientesComTotalMedicamentos() {
		String sql = """
				    SELECT
				        p.id AS id,
				        p.nome AS nome,
				        COUNT(mr.id_medicamento) AS totalPrescricoes
				    FROM paciente p
				    LEFT JOIN receituario r ON r.id_paciente = p.id
				    LEFT JOIN medicamento_receita mr ON mr.id_receituario = r.id
				    GROUP BY p.id, p.nome
				    ORDER BY totalPrescricoes DESC
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
