package br.tec.ici.saude.ManagedBean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

import br.tec.ici.saude.Repository.GenericLazyDataModel;
import br.tec.ici.saude.Repository.PacienteRepository;
import br.tec.ici.saude.model.Paciente;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named(value = "pacienteBean")
@ViewScoped
public class PacienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PacienteRepository repo;

	private Paciente paciente = new Paciente();

	private LazyDataModel<Paciente> pacientes;
	private GenericLazyDataModel<Paciente> gdm;
	private String filtroNome;
	private String filtroCpf;

	@PostConstruct
	public void init() {
		gdm = new GenericLazyDataModel<Paciente>(repo);
		pacientes = gdm;
	}

	public void salvar() {
		repo.salvar(paciente);
		paciente = new Paciente();
		msg();
	}

	public void novo() {
		paciente = new Paciente();
	}

	public void excluir(Paciente p) {
		repo.excluir(p.getId());
		msg();
	}

	public void editar(Paciente p) {
		paciente = repo.buscarPorId(p.getId());
	}

	public void pesquisar() {
		Map<String, Object> filtros = new HashMap<String, Object>();
		if (filtroNome != null && !filtroNome.isEmpty()) {
			filtros.put("nome", filtroNome);
		}
		if (filtroCpf != null && !filtroCpf.isEmpty()) {
			filtros.put("cpf", filtroCpf);
		}

		gdm.setFixedFilters(filtros);

		pacientes = gdm;
	}

	public LazyDataModel<Paciente> getPacientes() {
		return pacientes;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}

	public String getFiltroCpf() {
		return filtroCpf;
	}

	public void setFiltroCpf(String filtroCpf) {
		this.filtroCpf = filtroCpf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void msg() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Operação realizada!"));
	}
}
