package talenti.pro.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import talenti.pro.model.Competencia;
import talenti.pro.repository.CompetenciaRepository;

@RequestScoped
@Transactional
public class CompetenciaService {

	@Inject
	private CompetenciaRepository repository;

	public Competencia salvar(Competencia competencia) {
		if (competencia == null) {
			throw new IllegalArgumentException("Competencia não pode ser nulo.");
		}

		if (competencia.getNome() == null || competencia.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("Nome do Competencia é obrigatório.");
		}
		return repository.salvar(competencia);
	}

	@Transactional
	public void excluir(Long id) {
		repository.excluir(id);
	}

	public Optional<Competencia> buscarPorId(Long id) {
		return repository.buscarPorIdOpt(id);
	}

	public List<Competencia> listarTodos() {
		return repository.listar();
	}

	public List<Competencia> buscarPorNome(String nome, String cpf) {
		return repository.buscar(nome, cpf);
	}

	public List<Competencia> listarPaginado(int first, int pageSize, Map<String, Object> filtros) {
		return repository.listarPaginado(first, pageSize, filtros);
	}

	public Long contar(Map<String, Object> filtros) {
		return repository.contar(filtros);
	}

	public CompetenciaRepository getRepository() {
		return repository;
	}

}