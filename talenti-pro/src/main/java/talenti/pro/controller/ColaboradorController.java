package talenti.pro.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import talenti.pro.model.Cargo;
import talenti.pro.model.Colaborador;
import talenti.pro.model.Departamento;
import talenti.pro.model.VinculoEmpregativo;
import talenti.pro.repository.GenericLazyDataModel;
import talenti.pro.service.CargoService;
import talenti.pro.service.ColaboradorService;
import talenti.pro.service.DepartamentoService;
import talenti.pro.service.VinculoEmpregativoService;

@Named(value = "colaboradorController")
@ViewScoped
public class ColaboradorController extends ManagedBeanController {

	private static final long serialVersionUID = 1L;

	@Inject
	private ColaboradorService colaboradorService;

	@Inject
	private DepartamentoService departamentoService;

	@Inject
	private VinculoEmpregativoService vinculoEmpregativoService;

	@Inject
	private CargoService cargoService;

	private Colaborador colaborador = new Colaborador();
	private String filtroNome;

	private GenericLazyDataModel<Colaborador> colaboradors;
	private List<Cargo> cargos = new ArrayList<Cargo>();
	private List<Departamento> departamentos = new ArrayList<Departamento>();
	private List<VinculoEmpregativo> vinculoEmpregativos = new ArrayList<VinculoEmpregativo>();

	@PostConstruct
	public void init() {
		colaboradors = new GenericLazyDataModel<Colaborador>(colaboradorService);
		cargos = cargoService.listarTodos();
		departamentos = departamentoService.listarTodos();
		vinculoEmpregativos = vinculoEmpregativoService.listarTodos();
	}

	public void salvar() {
		colaboradorService.salvar(colaborador);
		novo();
		infoSucesso();
	}

	public void novo() {
		colaborador = new Colaborador();
	}

	public void excluir(Colaborador p) {
		colaboradorService.excluir(p.getId());
		infoSucesso();
	}

	public void editar(Colaborador p) {
		colaborador = colaboradorService.buscarPorId(p.getId());
	}

	public void pesquisar() {
		Map<String, Object> filtros = new HashMap<String, Object>();

		if (filtroNome != null && !filtroNome.isEmpty()) {
			filtros.put("nome", filtroNome);
		}

		colaboradors.setFixedFilters(filtros);
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public LazyDataModel<Colaborador> getColaboradors() {
		return colaboradors;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public List<VinculoEmpregativo> getVinculoEmpregativos() {
		return vinculoEmpregativos;
	}

}
