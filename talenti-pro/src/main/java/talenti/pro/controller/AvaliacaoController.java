package talenti.pro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.LazyDataModel;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import talenti.pro.model.Avaliacao;
import talenti.pro.model.Cargo;
import talenti.pro.model.Competencia;
import talenti.pro.model.Departamento;
import talenti.pro.model.VinculoEmpregativo;
import talenti.pro.repository.GenericLazyDataModel;
import talenti.pro.service.AvaliacaoService;
import talenti.pro.service.CargoService;
import talenti.pro.service.CompetenciaService;
import talenti.pro.service.DepartamentoService;
import talenti.pro.service.VinculoEmpregativoService;

@Named(value = "avaliacaoController")
@ViewScoped
public class AvaliacaoController extends ManagedBeanController {

	private static final long serialVersionUID = 1L;

	@Inject
	private AvaliacaoService avaliacaoService;
	
	@Inject
	private DepartamentoService departamentoService;
	
	@Inject
	private CargoService cargoService;
	
	@Inject
	private VinculoEmpregativoService vinculoEmpregativoService;
	
	@Inject
	private CompetenciaService competenciaService;
	
	private Avaliacao avaliacao = new Avaliacao();
	private String filtroNome;

	private GenericLazyDataModel<Avaliacao> avaliacaos;
	private List<Departamento> departamentos;
	private List<Cargo> cargos;
	private List<VinculoEmpregativo> vinculoEmpregativos;
	private List<Competencia> competencias;
	
	private String[] departamentoSelecionado;

	@PostConstruct
	public void init() {
		avaliacaos = new GenericLazyDataModel<Avaliacao>(avaliacaoService);
		departamentos = departamentoService.listarTodos();
		cargos = cargoService.listarTodos();
		vinculoEmpregativos = vinculoEmpregativoService.listarTodos();
		competencias = competenciaService.listarTodos();
	}

	public void salvar() {
		avaliacaoService.salvar(avaliacao);
		novo();
		infoSucesso();
	}

	public void novo() {
		avaliacao = new Avaliacao();
	}

	public void excluir(Avaliacao p) {
		avaliacaoService.excluir(p.getId());
		infoSucesso();
	}

	public void editar(Avaliacao p) {
		avaliacao = avaliacaoService.buscarPorId(p.getId());
	}

	public void pesquisar() {
		Map<String, Object> filtros = new HashMap<String, Object>();

		if (filtroNome != null && !filtroNome.isEmpty()) {
			filtros.put("nome", filtroNome);
		}

		avaliacaos.setFixedFilters(filtros);
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public LazyDataModel<Avaliacao> getAvaliacaos() {
		return avaliacaos;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}
	
	public List<Departamento> getDepartamentos() {
		return departamentos;
	}
	
	public List<Cargo> getCargos() {
		return cargos;
	}
	
	public List<VinculoEmpregativo> getVinculoEmpregativos() {
		return vinculoEmpregativos;
	}
	
	public List<Competencia> getCompetencias() {
		return competencias;
	}
	
	
	public void setDepartamentoSelecionado(String[] departamentoSelecionado) {
		this.departamentoSelecionado = departamentoSelecionado;
	}
	
	public String[] getDepartamentoSelecionado() {
		return departamentoSelecionado;
	}
	
	
	
    public void onToggleSelect(ToggleSelectEvent event) {
        FacesMessage msg = new FacesMessage();
        msg.setSummary("Toggled: " + event.isSelected());
        msg.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onItemSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage();
        msg.setSummary("Item selected: " + event.getObject().toString());
        msg.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    public void onItemUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage();
        msg.setSummary("Item unselected: " + event.getObject().toString());
        msg.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

	@Override
	public String getDescricaoTela() {
		return "Realize o gerenciamento das avaliações planejadas.";
	}

}
