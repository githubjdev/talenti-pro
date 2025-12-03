package talenti.pro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import talenti.pro.model.Departamento;
import talenti.pro.model.Gestor;
import talenti.pro.repository.GenericLazyDataModel;
import talenti.pro.service.DepartamentoService;
import talenti.pro.service.GestorService;

@Named(value = "departamentoController")
@ViewScoped
public class DepartamentoController extends ManagedBeanController {

	private static final long serialVersionUID = 1L;

	@Inject
	private DepartamentoService departamentoService;
	
	@Inject
	private GestorService gestorService;
	
	private Departamento departamento = new Departamento();
	private String filtroNome;

	private GenericLazyDataModel<Departamento> departamentos;
	private List<Gestor> gestors;

	@PostConstruct
	public void init() {
		departamentos = new GenericLazyDataModel<Departamento>(departamentoService);
		gestors = gestorService.listarTodos();
	}

	public void salvar() {
		departamentoService.salvar(departamento);
		novo();
		infoSucesso();
	}

	public void novo() {
		departamento = new Departamento();
	}

	public void excluir(Departamento p) {
		departamentoService.excluir(p.getId());
		infoSucesso();
	}

	public void editar(Departamento p) {
		departamento = departamentoService.buscarPorId(p.getId());
	}

	public void pesquisar() {
		Map<String, Object> filtros = new HashMap<String, Object>();

		if (filtroNome != null && !filtroNome.isEmpty()) {
			filtros.put("nome", filtroNome);
		}

		departamentos.setFixedFilters(filtros);
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public GenericLazyDataModel<Departamento> getDepartamentos() {
		return departamentos;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}
	
	public List<Gestor> getGestors() {
		return gestors;
	}
	
	@Override
	public String getDescricaoTela() {
		return "Realize o gerenciamento de cadastro dos departamentos.";
	}

}
