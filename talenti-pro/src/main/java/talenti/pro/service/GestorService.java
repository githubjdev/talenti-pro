package talenti.pro.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import talenti.pro.model.Gestor;
import talenti.pro.repository.GestorRepository;

@RequestScoped
public class GestorService implements ServiceInterface<Gestor> {

	@Inject
	private GestorRepository repository;

	@Transactional
	public Gestor salvar(Gestor gestor) {
		if (gestor == null) {
			throw new IllegalArgumentException("Gestor não pode ser nulo.");
		}

		if (gestor.getNome() == null || gestor.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("Nome da Gestor é obrigatório.");
		}
		return repository.salvar(gestor);
	}

	@Transactional
	public void excluir(Long id) {
		repository.excluir(id);
	}

	public List<Gestor> listarTodos() {
		return repository.listar();
	}

	public List<Gestor> buscarPorNome(String nome, String cpf) {
		return repository.buscar(nome, cpf);
	}

	public List<Gestor> listarPaginado(int first, int pageSize, Map<String, Object> filtros) {
		return repository.listarPaginado(first, pageSize, filtros);
	}

	public Long contar(Map<String, Object> filtros) {
		return repository.contar(filtros);
	}

	@Override
	public Optional<Gestor> buscarPorIdOpt(Long id) {
		return repository.buscarPorIdOpt(id);
	}

	@Override
	public Gestor buscarPorId(Long id) {
		return repository.buscarPorId(id);
	}

}