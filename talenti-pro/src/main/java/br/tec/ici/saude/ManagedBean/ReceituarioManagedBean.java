package br.tec.ici.saude.ManagedBean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

import br.tec.ici.saude.Repository.GenericLazyDataModel;
import br.tec.ici.saude.Repository.MedicamentoRepository;
import br.tec.ici.saude.Repository.PacienteRepository;
import br.tec.ici.saude.Repository.ReceituarioRepository;
import br.tec.ici.saude.model.Medicamento;
import br.tec.ici.saude.model.MedicamentoReceita;
import br.tec.ici.saude.model.Paciente;
import br.tec.ici.saude.model.Receituario;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named(value = "receituarioManagedBean")
@ViewScoped
public class ReceituarioManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PacienteRepository repoPac;
	@Inject
	private MedicamentoRepository repoMed;

	@Inject
	private ReceituarioRepository repoRec;

	private Paciente paciente = new Paciente();
	private Medicamento medicamento = new Medicamento();
	private Receituario receituario = new Receituario();

	private LazyDataModel<Paciente> pacientes;
	private LazyDataModel<Medicamento> medicamentos;
	private LazyDataModel<Receituario> receitas;

	private GenericLazyDataModel<Paciente> gdmPac;
	private GenericLazyDataModel<Medicamento> gdmMed;
	private GenericLazyDataModel<Receituario> gdmRece;
	private String filtroNomePaciente;
	private String filtroNomeMedicamento;
	private String obs;
	private Double quantidade = 0.0;

	@PostConstruct
	public void init() {
		gdmPac = new GenericLazyDataModel<Paciente>(repoPac);
		pacientes = gdmPac;

		gdmMed = new GenericLazyDataModel<Medicamento>(repoMed);
		medicamentos = gdmMed;

		gdmRece = new GenericLazyDataModel<Receituario>(repoRec);
		receitas = gdmRece;
	}

	public void pesquisarPaciente() {
		Map<String, Object> filtros = new HashMap<String, Object>();
		if (filtroNomePaciente != null && !filtroNomePaciente.isEmpty()) {
			filtros.put("nome", filtroNomePaciente);
		}

		gdmPac.setFixedFilters(filtros);

		pacientes = gdmPac;
	}

	public void pesquisarMedicamento() {
		Map<String, Object> filtros = new HashMap<String, Object>();
		if (filtroNomeMedicamento != null && !filtroNomeMedicamento.isEmpty()) {
			filtros.put("nome", filtroNomeMedicamento);
		}

		gdmMed.setFixedFilters(filtros);

		medicamentos = gdmMed;
	}

	public void addPaciente(Paciente p) {
		this.paciente = p;
		receituario.setPaciente(p);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
				"Paciente " + p.getNome() + " adicionado na receita!"));
	}

	public void addMedicamento(Medicamento m) {
		this.medicamento = m;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
				"Medicamento " + m.getNome() + " selecionado!"));
	}

	public void addReceitaMedicamento() {

		if (quantidade <= 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Informe a quantidade do medicamento!"));
			return;
		}

		if (paciente.getId() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Selecione o paciente!"));
			return;
		}

		if (medicamento.getId() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Selecione o medicamento!"));
			return;
		}

		MedicamentoReceita prescricao = new MedicamentoReceita();
		prescricao.setMedicamento(medicamento);
		prescricao.setReceituario(receituario);
		prescricao.setQuantidade(quantidade);
		prescricao.setObs(obs);
		receituario.getMedicamentos().add(prescricao);

		quantidade = 0.0;
		obs = "Nenhuma";

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Prescrição adicionada com sucesso!"));
	}

	public void salvarReceitar() {

		if (paciente == null || paciente.getId() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Selecione o paciente!"));
			return;
		}

		if (medicamento == null || medicamento.getId() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Selecione o medicamento!"));
			return;
		}

		if (receituario.getMedicamentos().size() <= 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Deve ser infromado algum medicamento!"));
			return;
		}

		repoRec.salvar(receituario);

		receituario = new Receituario();
		medicamento = new Medicamento();

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Receita salva com sucesso!"));
	}

	public void removerMedicamento(MedicamentoReceita m) {
		receituario.getMedicamentos().remove(m);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Pescrição removida!"));
	}

	public String getFiltroNomePaciente() {
		return filtroNomePaciente;
	}

	public void setFiltroNomePaciente(String filtroNomePaciente) {
		this.filtroNomePaciente = filtroNomePaciente;
	}

	public String getFiltroNomeMedicamento() {
		return filtroNomeMedicamento;
	}

	public void setFiltroNomeMedicamento(String filtroNomeMedicamento) {
		this.filtroNomeMedicamento = filtroNomeMedicamento;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Receituario getReceituario() {
		return receituario;
	}

	public void setReceituario(Receituario receituario) {
		this.receituario = receituario;
	}

	public LazyDataModel<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(LazyDataModel<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public LazyDataModel<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(LazyDataModel<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public LazyDataModel<Receituario> getReceitas() {
		return receitas;
	}

	public void msg() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Operação realizada!"));
	}

}
