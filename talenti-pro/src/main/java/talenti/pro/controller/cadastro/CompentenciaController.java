package talenti.pro.controller.cadastro;

import java.util.HashMap;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import talenti.pro.controller.ManagedBeanController;
import talenti.pro.model.cadastro.Competencia;
import talenti.pro.repository.GenericLazyDataModel;
import talenti.pro.service.cadastro.CompetenciaService;

@Named(value = "competenciaController")
@ViewScoped
public class CompentenciaController extends ManagedBeanController {

	private static final long serialVersionUID = 1L;

	@Inject
	private CompetenciaService competenciaService;
	private Competencia competencia = new Competencia();
	private String filtroNome;

	private GenericLazyDataModel<Competencia> competencias;

	@PostConstruct
	public void init() {
		competencias = new GenericLazyDataModel<Competencia>(competenciaService);
	}

	public void salvar() {
		competenciaService.salvar(competencia);
		novo();
		infoSucesso();
	}

	public void novo() {
		competencia = new Competencia();
	}

	public void excluir(Competencia p) {
		competenciaService.excluir(p.getId());
		infoSucesso();
	}

	public void editar(Competencia p) {
		competencia = competenciaService.buscarPorId(p.getId());
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
	
	@Override
	public String getDescricaoTela() {
		return "Realize o gerenciamento do cadastro das competências.";
	}

}
