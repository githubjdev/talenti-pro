package talenti.pro.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import talenti.pro.model.Departamento;
import talenti.pro.repository.DepartamentoRepository;

@RequestScoped
public class DepartamentoService implements ServiceInterface<Departamento> {

	@Inject
	private DepartamentoRepository repository;

	@Transactional
	public Departamento salvar(Departamento departamento) {
		if (departamento == null) {
			throw new IllegalArgumentException("Departamento não pode ser nulo.");
		}

		if (departamento.getNome() == null || departamento.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("Nome da Departamento é obrigatório.");
		}
		return repository.salvar(departamento);
	}

	@Transactional
	public void excluir(Long id) {
		repository.excluir(id);
	}

	public List<Departamento> listarTodos() {
		return repository.listar();
	}

	public List<Departamento> buscarPorNome(String nome) {
		return repository.buscar(nome);
	}

	public List<Departamento> listarPaginado(int first, int pageSize, Map<String, Object> filtros) {
		return repository.listarPaginado(first, pageSize, filtros);
	}

	public Long contar(Map<String, Object> filtros) {
		return repository.contar(filtros);
	}

	@Override
	public Optional<Departamento> buscarPorIdOpt(Long id) {
		return repository.buscarPorIdOpt(id);
	}

	@Override
	public Departamento buscarPorId(Long id) {
		return repository.buscarPorId(id);
	}

}