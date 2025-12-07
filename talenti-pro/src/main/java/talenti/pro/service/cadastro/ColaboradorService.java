package talenti.pro.service.cadastro;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import talenti.pro.exceptionhandler.ValidacaoException;
import talenti.pro.model.cadastro.Colaborador;
import talenti.pro.repository.cadastro.ColaboradorRepository;

@RequestScoped
public class ColaboradorService implements ServiceInterface<Colaborador> {

	@Inject
	private ColaboradorRepository repository;

	@Transactional
	public Colaborador salvar(Colaborador colaborador) {
		if (colaborador == null) {
			throw new ValidacaoException("Colaborador não pode ser nulo.");
		}

		if (colaborador.getNome() == null || colaborador.getNome().trim().isEmpty()) {
			throw new ValidacaoException("Nome do Colaborador é obrigatório.");
		}
		return repository.salvar(colaborador);
	}

	@Transactional
	public void excluir(Long id) {
		repository.excluir(id);
	}

	public List<Colaborador> listarTodos() {
		return repository.listar();
	}

	public List<Colaborador> buscarPorNome(String nome, String cpf) {
		return repository.buscar(nome, cpf);
	}

	public List<Colaborador> listarPaginado(int first, int pageSize, Map<String, Object> filtros) {
		return repository.listarPaginado(first, pageSize, filtros);
	}

	public Long contar(Map<String, Object> filtros) {
		return repository.contar(filtros);
	}

	@Override
	public Optional<Colaborador> buscarPorIdOpt(Long id) {
		return repository.buscarPorIdOpt(id);
	}

	@Override
	public Colaborador buscarPorId(Long id) {
		return repository.buscarPorId(id);
	}

}