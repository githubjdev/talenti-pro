package talenti.pro.controller.cadastro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import talenti.pro.controller.ManagedBeanController;
import talenti.pro.model.cadastro.Competencia;
import talenti.pro.model.cadastro.Pergunta;
import talenti.pro.repository.GenericLazyDataModel;
import talenti.pro.service.cadastro.CompetenciaService;
import talenti.pro.service.cadastro.PerguntaService;

@Named(value = "perguntaController")
@ViewScoped
public class PerguntaController extends ManagedBeanController {

	private static final long serialVersionUID = 1L;

	@Inject
	private PerguntaService perguntaService;
	
	@Inject
	private CompetenciaService competenciaService;
	
	private Pergunta pergunta = new Pergunta();
	private String filtroNome;

	private GenericLazyDataModel<Pergunta> perguntas;
	private List<Competencia> competencias;

	@PostConstruct
	public void init() {
		perguntas = new GenericLazyDataModel<Pergunta>(perguntaService);
		competencias = competenciaService.listarTodos();
	}

	public void salvar() {
		perguntaService.salvar(pergunta);
		novo();
		infoSucesso();
	}

	public void novo() {
		pergunta = new Pergunta();
	}

	public void excluir(Pergunta p) {
		perguntaService.excluir(p.getId());
		infoSucesso();
	}

	public void editar(Pergunta p) {
		pergunta = perguntaService.buscarPorId(p.getId());
	}

	public void pesquisar() {
		Map<String, Object> filtros = new HashMap<String, Object>();

		if (filtroNome != null && !filtroNome.isEmpty()) {
			filtros.put("nome", filtroNome);
		}

		perguntas.setFixedFilters(filtros);
	}
	
	
	public List<Competencia> getCompetencias() {
		return competencias;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public LazyDataModel<Pergunta> getPerguntas() {
		return perguntas;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}
	
	
	@Override
	public String getDescricaoTela() {
		return "Realize o gerenciamento de cadastro das perguntas das avaliações.";
	}

}
