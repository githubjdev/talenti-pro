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
import talenti.pro.model.Competencia;
import talenti.pro.repository.GenericLazyDataModel;
import talenti.pro.service.CompetenciaService;

@Named(value = "competenciaController")
@ViewScoped
public class CompentenciaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CompetenciaService competenciaService;
	private Competencia competencia = new Competencia();
	private GenericLazyDataModel<Competencia> competencias;
	private String filtroNome;

	@PostConstruct
	public void init() {
		competencias = new GenericLazyDataModel<Competencia>(competenciaService.getRepository());
	}

	public void salvar() {
		competenciaService.salvar(competencia);
		competencia = new Competencia();
		msg();
	}

	public void novo() {
		competencia = new Competencia();
	}

	public void excluir(Competencia p) {
		competenciaService.excluir(p.getId());
		msg();
	}

	public void editar(Competencia p) {
		competencia = competenciaService.buscarPorId(p.getId()).get();
	}

	public void pesquisar() {
		Map<String, Object> filtros = new HashMap<String, Object>();

		if (filtroNome != null && !filtroNome.isEmpty()) {
			filtros.put("nome", filtroNome);
		}

		competencias.setFixedFilters(filtros);
	}


	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	public LazyDataModel<Competencia> getCompetencias() {
		return competencias;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}

	public void setCompetencias(GenericLazyDataModel<Competencia> competencias) {
		this.competencias = competencias;
	}

	public void msg() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Operação realizada!"));
	}
}
