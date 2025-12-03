package talenti.pro.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import talenti.pro.model.Avaliacao;
import talenti.pro.repository.AvaliacaoRepository;

@RequestScoped
public class AvaliacaoService implements ServiceInterface<Avaliacao> {

	@Inject
	private AvaliacaoRepository repository;

	@Transactional
	public Avaliacao salvar(Avaliacao avaliacao) {
		if (avaliacao == null) {
			throw new IllegalArgumentException("Avaliacao não pode ser nulo.");
		}

		if (avaliacao.getNome() == null || avaliacao.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("Nome do Avaliação é obrigatório.");
		}
		return repository.salvar(avaliacao);
	}

	@Transactional
	public void excluir(Long id) {
		repository.excluir(id);
	}

	public List<Avaliacao> listarTodos() {
		return repository.listar();
	}

	public List<Avaliacao> buscarPorNome(String nome) {
		return repository.buscar(nome);
	}

	public List<Avaliacao> listarPaginado(int first, int pageSize, Map<String, Object> filtros) {
		return repository.listarPaginado(first, pageSize, filtros);
	}

	public Long contar(Map<String, Object> filtros) {
		return repository.contar(filtros);
	}

	@Override
	public Optional<Avaliacao> buscarPorIdOpt(Long id) {
		return repository.buscarPorIdOpt(id);
	}

	@Override
	public Avaliacao buscarPorId(Long id) {
		return repository.buscarPorId(id);
	}

}