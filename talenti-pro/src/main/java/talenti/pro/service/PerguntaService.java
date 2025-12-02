package talenti.pro.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import talenti.pro.model.Pergunta;
import talenti.pro.repository.PerguntaRepository;

@RequestScoped
public class PerguntaService implements ServiceInterface<Pergunta> {

	@Inject
	private PerguntaRepository repository;

	@Transactional
	public Pergunta salvar(Pergunta pergunta) {
		if (pergunta == null) {
			throw new IllegalArgumentException("Pergunta não pode ser nulo.");
		}

		if (pergunta.getDescricao() == null || pergunta.getDescricao().trim().isEmpty()) {
			throw new IllegalArgumentException("Descrição do Pergunta é obrigatório.");
		}
		return repository.salvar(pergunta);
	}

	@Transactional
	public void excluir(Long id) {
		repository.excluir(id);
	}

	public List<Pergunta> listarTodos() {
		return repository.listar();
	}


	public List<Pergunta> listarPaginado(int first, int pageSize, Map<String, Object> filtros) {
		return repository.listarPaginado(first, pageSize, filtros);
	}

	public Long contar(Map<String, Object> filtros) {
		return repository.contar(filtros);
	}

	@Override
	public Optional<Pergunta> buscarPorIdOpt(Long id) {
		return repository.buscarPorIdOpt(id);
	}

	@Override
	public Pergunta buscarPorId(Long id) {
		return repository.buscarPorId(id);
	}

}