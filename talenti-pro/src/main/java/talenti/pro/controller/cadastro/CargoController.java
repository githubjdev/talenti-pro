package talenti.pro.controller.cadastro;

import java.util.HashMap;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import talenti.pro.controller.ManagedBeanController;
import talenti.pro.model.cadastro.Cargo;
import talenti.pro.repository.GenericLazyDataModel;
import talenti.pro.service.cadastro.CargoService;

@Named(value = "cargoController")
@ViewScoped
public class CargoController extends ManagedBeanController {

	private static final long serialVersionUID = 1L;

	@Inject
	private CargoService cargoService;
	private Cargo cargo = new Cargo();
	private String filtroNome;

	private GenericLazyDataModel<Cargo> cargos;

	@PostConstruct
	public void init() {
		cargos = new GenericLazyDataModel<Cargo>(cargoService);
	}

	public void salvar() {
		cargoService.salvar(cargo);
		novo();
		infoSucesso();
	}

	public void novo() {
		cargo = new Cargo();
	}

	public void excluir(Cargo p) {
		cargoService.excluir(p.getId());
		infoSucesso();
	}

	public void editar(Cargo p) {
		cargo = cargoService.buscarPorId(p.getId());
	}

	public void pesquisar() {
		Map<String, Object> filtros = new HashMap<String, Object>();

		if (filtroNome != null && !filtroNome.isEmpty()) {
			filtros.put("nome", filtroNome);
		}

		cargos.setFixedFilters(filtros);
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public LazyDataModel<Cargo> getCargos() {
		return cargos;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}
	
	@Override
	public String getDescricaoTela() {
		return "Realize o gerenciamento de cadastro do cargos.";
	}

}
