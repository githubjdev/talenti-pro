package talenti.pro.service.cadastro;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import talenti.pro.exceptionhandler.ValidacaoException;
import talenti.pro.model.cadastro.Competencia;
import talenti.pro.repository.cadastro.CompetenciaRepository;

@RolesAllowed("ROLE_USER")
@RequestScoped
public class CompetenciaService implements ServiceInterface<Competencia> {

	@Inject
	private CompetenciaRepository repository;

	@Transactional
	public Competencia salvar(Competencia competencia) {
		if (competencia == null) {
			throw new ValidacaoException("Competencia não pode ser nulo.");
		}

		if (competencia.getNome() == null || competencia.getNome().trim().isEmpty()) {
			throw new ValidacaoException("Nome da Competencia é obrigatório.");
		}
		return repository.salvar(competencia);
	}

	@Transactional
	public void excluir(Long id) {
		repository.excluir(id);
	}

	public List<Competencia> listarTodos() {
		return repository.listar();
	}

	public List<Competencia> buscarPorNome(String nome) {
		return repository.buscar(nome);
	}

	public List<Competencia> listarPaginado(int first, int pageSize, Map<String, Object> filtros) {
		return repository.listarPaginado(first, pageSize, filtros);
	}

	public Long contar(Map<String, Object> filtros) {
		return repository.contar(filtros);
	}

	@Override
	public Optional<Competencia> buscarPorIdOpt(Long id) {
		return repository.buscarPorIdOpt(id);
	}

	@Override
	public Competencia buscarPorId(Long id) {
		return repository.buscarPorId(id);
	}

}