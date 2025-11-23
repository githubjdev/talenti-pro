package talenti.pro.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import talenti.pro.model.Medicamento;
import talenti.pro.repository.MedicamentoRepository;

@RequestScoped
public class MedicamentoService {

	@Inject
	private MedicamentoRepository repository;

	@Transactional
	public Medicamento salvar(Medicamento medicamento) {
		validarMedicamento(medicamento);
		return repository.salvar(medicamento);
	}

	@Transactional
	public void excluir(Long id) {
		repository.excluir(id);
	}

	public Optional<Medicamento> buscarPorId(Long id) {
		return repository.buscarPorIdOpt(id);
	}

	public List<Medicamento> listarTodos() {
		return repository.listar();
	}

	public List<Medicamento> buscarPorNome(String nome, String cpf) {
		return repository.buscar(nome, cpf);
	}

	public List<Medicamento> listarPaginado(int first, int pageSize, Map<String, Object> filtros) {
		return repository.listarPaginado(first, pageSize, filtros);
	}

	public Long contar(Map<String, Object> filtros) {
		return repository.contar(filtros);
	}

	private void validarMedicamento(Medicamento medicamento) {

		if (medicamento == null) {
			throw new IllegalArgumentException("Medicamento não pode ser nulo.");
		}

		if (medicamento.getNome() == null || medicamento.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("Nome do medicamento é obrigatório.");
		}

	}
}