package talenti.pro.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import talenti.pro.model.Medicamento;
import talenti.pro.repository.GenericLazyDataModel;
import talenti.pro.repository.MedicamentoRepository;

@Named(value = "medicamentoBean")
@ViewScoped
public class MedicamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MedicamentoRepository repo;

	private Medicamento medicamento = new Medicamento();

	private LazyDataModel<Medicamento> medicamentos;
	private GenericLazyDataModel<Medicamento> gdm;
	private String filtroNome;

	@PostConstruct
	public void init() {
		gdm = new GenericLazyDataModel<Medicamento>(repo);
		medicamentos = gdm;
	}

	public void salvar() {
		repo.salvar(medicamento);
		medicamento = new Medicamento();
		msg();
	}

	public void novo() {
		medicamento = new Medicamento();
	}

	public void excluir(Medicamento p) {
		repo.excluir(p.getId());
		msg();
	}

	public void editar(Medicamento p) {
		medicamento = repo.buscarPorId(p.getId());
	}

	public void pesquisar() {
		Map<String, Object> filtros = new HashMap<String, Object>();
		if (filtroNome != null && !filtroNome.isEmpty()) {
			filtros.put("nome", filtroNome);
		}

		gdm.setFixedFilters(filtros);

		medicamentos = gdm;
	}

	public LazyDataModel<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void msg() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Operação realizada!"));
	}
}
