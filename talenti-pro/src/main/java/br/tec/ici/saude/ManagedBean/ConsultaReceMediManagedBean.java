package br.tec.ici.saude.ManagedBean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

import br.tec.ici.saude.Repository.GenericLazyDataModel;
import br.tec.ici.saude.Repository.MedicamentoRepository;
import br.tec.ici.saude.Repository.ReceituarioRepository;
import br.tec.ici.saude.model.Medicamento;
import br.tec.ici.saude.model.Receituario;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named(value = "consultaReceMediManagedBean")
@ViewScoped
public class ConsultaReceMediManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MedicamentoRepository repoMed;

	@Inject
	private ReceituarioRepository repoRec;

	private LazyDataModel<Medicamento> medicamentos;
	private LazyDataModel<Receituario> receitas;

	private GenericLazyDataModel<Receituario> gdmRece;
	private GenericLazyDataModel<Medicamento> gdmMed;
	private String filtroNomeMedicamento;

	@PostConstruct
	public void init() {
		gdmMed = new GenericLazyDataModel<Medicamento>(repoMed);
		medicamentos = gdmMed;

	}

	public void pesquisarMedicamento() {
		Map<String, Object> filtros = new HashMap<String, Object>();
		if (filtroNomeMedicamento != null && !filtroNomeMedicamento.isEmpty()) {
			filtros.put("nome", filtroNomeMedicamento);
		}

		gdmMed.setFixedFilters(filtros);

		medicamentos = gdmMed;
	}

	public void selectMedicamento(Medicamento m) {
		Map<String, Object> filtros = new HashMap<String, Object>();
		filtros.put("idmedicamento", m.getId());

		gdmRece = new GenericLazyDataModel<Receituario>(repoRec);
		gdmRece.setFixedFilters(filtros);
		receitas = gdmRece;

	}

	public void setFiltroNomeMedicamento(String filtroNomeMedicamento) {
		this.filtroNomeMedicamento = filtroNomeMedicamento;
	}

	public String getFiltroNomeMedicamento() {
		return filtroNomeMedicamento;
	}

	public LazyDataModel<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(LazyDataModel<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
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
