package br.tec.ici.saude.ManagedBean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

import br.tec.ici.saude.Repository.GenericLazyDataModel;
import br.tec.ici.saude.Repository.PacienteRepository;
import br.tec.ici.saude.Repository.ReceituarioRepository;
import br.tec.ici.saude.model.Paciente;
import br.tec.ici.saude.model.Receituario;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named(value = "consultaRecePacManagedBean")
@ViewScoped
public class ConsultaRecePacienteManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PacienteRepository repoPac;

	@Inject
	private ReceituarioRepository repoRec;

	private Paciente paciente = new Paciente();

	private LazyDataModel<Paciente> pacientes;
	private LazyDataModel<Receituario> receitas;

	private GenericLazyDataModel<Receituario> gdmRece;
	private GenericLazyDataModel<Paciente> gdmPac;
	private String filtroNomePaciente;

	@PostConstruct
	public void init() {
		gdmPac = new GenericLazyDataModel<Paciente>(repoPac);
		pacientes = gdmPac;

	}

	public void pesquisarPaciente() {
		Map<String, Object> filtros = new HashMap<String, Object>();
		if (filtroNomePaciente != null && !filtroNomePaciente.isEmpty()) {
			filtros.put("nome", filtroNomePaciente);
		}

		gdmPac.setFixedFilters(filtros);

		pacientes = gdmPac;
	}

	public void selectPaciente(Paciente p) {
		Map<String, Object> filtros = new HashMap<String, Object>();
		filtros.put("idpaciente", p.getId());

		gdmRece = new GenericLazyDataModel<Receituario>(repoRec);
		gdmRece.setFixedFilters(filtros);
		receitas = gdmRece;

	}

	public String getFiltroNomePaciente() {
		return filtroNomePaciente;
	}

	public void setFiltroNomePaciente(String filtroNomePaciente) {
		this.filtroNomePaciente = filtroNomePaciente;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LazyDataModel<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(LazyDataModel<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public LazyDataModel<Receituario> getReceitas() {
		return receitas;
	}

	public void setReceitas(LazyDataModel<Receituario> receitas) {
		this.receitas = receitas;
	}

	public void msg() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Operação realizada!"));
	}

}
