package talenti.pro.controller;

import java.util.HashMap;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import talenti.pro.model.Gestor;
import talenti.pro.repository.GenericLazyDataModel;
import talenti.pro.service.GestorService;

@Named(value = "gestorController")
@ViewScoped
public class GestorController extends ManagedBeanController {

	private static final long serialVersionUID = 1L;

	@Inject
	private GestorService gestorService;
	private Gestor gestor = new Gestor();
	private String filtroNome;

	private GenericLazyDataModel<Gestor> gestores;

	@PostConstruct
	public void init() {
		gestores = new GenericLazyDataModel<Gestor>(gestorService);
	}

	public void salvar() {
		gestorService.salvar(gestor);
		novo();
		infoSucesso();
	}

	public void novo() {
		gestor = new Gestor();
	}

	public void excluir(Gestor p) {
		gestorService.excluir(p.getId());
		infoSucesso();
	}

	public void editar(Gestor p) {
		gestor = gestorService.buscarPorId(p.getId());
	}

	public void pesquisar() {
		Map<String, Object> filtros = new HashMap<String, Object>();

		if (filtroNome != null && !filtroNome.isEmpty()) {
			filtros.put("nome", filtroNome);
		}

		gestores.setFixedFilters(filtros);
	}

	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}

	public LazyDataModel<Gestor> getGestors() {
		return gestores;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}
	
	
	@Override
	public String getDescricaoTela() {
		return "Realize o gerenciamento de cadastro dos gestores";
	}

}
