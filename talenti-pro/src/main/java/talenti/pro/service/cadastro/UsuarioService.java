package talenti.pro.service.cadastro;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import talenti.pro.exceptionhandler.ValidacaoException;
import talenti.pro.model.Usuario;
import talenti.pro.repository.cadastro.UsuarioRepository;

@RolesAllowed("ROLE_USER")
@RequestScoped
public class UsuarioService implements ServiceInterface<Usuario> {

	@Inject
	private UsuarioRepository repository;

	@Transactional
	public Usuario salvar(Usuario cargo) {
		if (cargo == null) {
			throw new ValidacaoException("Usuario não pode ser nulo.");
		}

		if (cargo.getLogin() == null || cargo.getLogin().trim().isEmpty()) {
			throw new ValidacaoException("Login do Usuário é obrigatório.");
		}
		return repository.salvar(cargo);
	}

	@Transactional
	public void excluir(Long id) {
		repository.excluir(id);
	}

	public List<Usuario> listarTodos() {
		return repository.listar();
	}

	public List<Usuario> buscarPorNome(String nome) {
		return repository.buscar(nome);
	}

	public List<Usuario> listarPaginado(int first, int pageSize, Map<String, Object> filtros) {
		return repository.listarPaginado(first, pageSize, filtros);
	}

	public Long contar(Map<String, Object> filtros) {
		return repository.contar(filtros);
	}

	@Override
	public Optional<Usuario> buscarPorIdOpt(Long id) {
		return repository.buscarPorIdOpt(id);
	}

	@Override
	public Usuario buscarPorId(Long id) {
		return repository.buscarPorId(id);
	}

}