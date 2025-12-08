package talenti.pro.controller.cadastro;

import java.util.HashMap;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.RolesAllowed;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import talenti.pro.controller.ManagedBeanController;
import talenti.pro.model.cadastro.Prefeitura;
import talenti.pro.repository.GenericLazyDataModel;
import talenti.pro.service.cadastro.PrefeituraService;

@RolesAllowed({"ROLE_MASTER"})
@Named(value = "prefeituraController")
@ViewScoped
public class PrefeituraController extends ManagedBeanController {

	private static final long serialVersionUID = 1L;

	@Inject
	private PrefeituraService prefeituraService;
	private Prefeitura prefeitura = new Prefeitura();
	private String filtroNome;

	private GenericLazyDataModel<Prefeitura> prefeituras;

	@PostConstruct
	public void init() {
		prefeituras = new GenericLazyDataModel<Prefeitura>(prefeituraService);
	}

	public void salvar() {
		prefeituraService.salvar(prefeitura);
		novo();
		infoSucesso();
	}

	public void novo() {
		prefeitura = new Prefeitura();
	}

	public void excluir(Prefeitura p) {
		prefeituraService.excluir(p.getId());
		infoSucesso();
	}

	public void editar(Prefeitura p) {
		prefeitura = prefeituraService.buscarPorId(p.getId());
	}

	public void pesquisar() {
		Map<String, Object> filtros = new HashMap<String, Object>();

		if (filtroNome != null && !filtroNome.isEmpty()) {
			filtros.put("nome", filtroNome);
		}

		prefeituras.setFixedFilters(filtros);
	}

	public Prefeitura getPrefeitura() {
		return prefeitura;
	}

	public void setPrefeitura(Prefeitura prefeitura) {
		this.prefeitura = prefeitura;
	}

	public LazyDataModel<Prefeitura> getPrefeituras() {
		return prefeituras;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}
	
	@Override
	public String getDescricaoTela() {
		return "Realize o gerenciamento de cadastro do prefeituras.";
	}

}
