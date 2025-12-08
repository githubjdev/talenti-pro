package talenti.pro.service.cadastro;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import talenti.pro.exceptionhandler.ValidacaoException;
import talenti.pro.model.cadastro.Prefeitura;
import talenti.pro.repository.cadastro.PrefeituraRepository;

@RolesAllowed({"ROLE_MASTER"})
@RequestScoped
public class PrefeituraService implements ServiceInterface<Prefeitura> {

	@Inject
	private PrefeituraRepository repository;

	@Transactional
	public Prefeitura salvar(Prefeitura prefeitura) {
		if (prefeitura == null) {
			throw new ValidacaoException("Prefeitura não pode ser nulo.");
		}

		if (prefeitura.getNome() == null || prefeitura.getNome().trim().isEmpty()) {
			throw new ValidacaoException("Nome da Prefeitura é obrigatório.");
		}
		return repository.salvar(prefeitura);
	}

	@Transactional
	public void excluir(Long id) {
		repository.excluir(id);
	}

	public List<Prefeitura> listarTodos() {
		return repository.listar();
	}

	public List<Prefeitura> buscarPorNome(String nome) {
		return repository.buscar(nome);
	}

	public List<Prefeitura> listarPaginado(int first, int pageSize, Map<String, Object> filtros) {
		return repository.listarPaginado(first, pageSize, filtros);
	}

	public Long contar(Map<String, Object> filtros) {
		return repository.contar(filtros);
	}

	@Override
	public Optional<Prefeitura> buscarPorIdOpt(Long id) {
		return repository.buscarPorIdOpt(id);
	}

	@Override
	public Prefeitura buscarPorId(Long id) {
		return repository.buscarPorId(id);
	}

}