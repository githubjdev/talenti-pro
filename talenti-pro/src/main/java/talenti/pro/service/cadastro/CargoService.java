package talenti.pro.service.cadastro;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import talenti.pro.exceptionhandler.ValidacaoException;
import talenti.pro.model.cadastro.Cargo;
import talenti.pro.repository.cadastro.CargoRepository;

@RequestScoped
public class CargoService implements ServiceInterface<Cargo> {

	@Inject
	private CargoRepository repository;

	@Transactional
	public Cargo salvar(Cargo cargo) {
		if (cargo == null) {
			throw new ValidacaoException("Cargo não pode ser nulo.");
		}

		if (cargo.getNome() == null || cargo.getNome().trim().isEmpty()) {
			throw new ValidacaoException("Nome do Cargo é obrigatório.");
		}
		return repository.salvar(cargo);
	}

	@Transactional
	public void excluir(Long id) {
		repository.excluir(id);
	}

	public List<Cargo> listarTodos() {
		return repository.listar();
	}

	public List<Cargo> buscarPorNome(String nome) {
		return repository.buscar(nome);
	}

	public List<Cargo> listarPaginado(int first, int pageSize, Map<String, Object> filtros) {
		return repository.listarPaginado(first, pageSize, filtros);
	}

	public Long contar(Map<String, Object> filtros) {
		return repository.contar(filtros);
	}

	@Override
	public Optional<Cargo> buscarPorIdOpt(Long id) {
		return repository.buscarPorIdOpt(id);
	}

	@Override
	public Cargo buscarPorId(Long id) {
		return repository.buscarPorId(id);
	}

}