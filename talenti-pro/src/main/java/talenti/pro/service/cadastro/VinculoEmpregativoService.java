package talenti.pro.service.cadastro;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import talenti.pro.model.cadastro.VinculoEmpregativo;
import talenti.pro.repository.cadastro.VinculoEmpregativoRepository;

@RequestScoped
public class VinculoEmpregativoService implements ServiceInterface<VinculoEmpregativo> {

	@Inject
	private VinculoEmpregativoRepository repository;

	@Transactional
	public VinculoEmpregativo salvar(VinculoEmpregativo vinculo_empregativo) {
		if (vinculo_empregativo == null) {
			throw new IllegalArgumentException("VinculoEmpregativo não pode ser nulo.");
		}

		if (vinculo_empregativo.getNome() == null || vinculo_empregativo.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("Nome do VinculoEmpregativo é obrigatório.");
		}
		return repository.salvar(vinculo_empregativo);
	}

	@Transactional
	public void excluir(Long id) {
		repository.excluir(id);
	}

	public List<VinculoEmpregativo> listarTodos() {
		return repository.listar();
	}

	public List<VinculoEmpregativo> buscarPorNome(String nome) {
		return repository.buscar(nome);
	}

	public List<VinculoEmpregativo> listarPaginado(int first, int pageSize, Map<String, Object> filtros) {
		return repository.listarPaginado(first, pageSize, filtros);
	}

	public Long contar(Map<String, Object> filtros) {
		return repository.contar(filtros);
	}

	@Override
	public Optional<VinculoEmpregativo> buscarPorIdOpt(Long id) {
		return repository.buscarPorIdOpt(id);
	}

	@Override
	public VinculoEmpregativo buscarPorId(Long id) {
		return repository.buscarPorId(id);
	}

}