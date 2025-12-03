package talenti.pro.controller;

import java.util.HashMap;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import talenti.pro.model.VinculoEmpregativo;
import talenti.pro.repository.GenericLazyDataModel;
import talenti.pro.service.VinculoEmpregativoService;

@Named(value = "vinculoEmpregativoController")
@ViewScoped
public class VinculoEmpregativoController extends ManagedBeanController {

	private static final long serialVersionUID = 1L;

	@Inject
	private VinculoEmpregativoService vinculoEmpregativoService;
	private VinculoEmpregativo vinculoEmpregativo = new VinculoEmpregativo();
	private String filtroNome;

	private GenericLazyDataModel<VinculoEmpregativo> vinculoEmpregativos;

	@PostConstruct
	public void init() {
		vinculoEmpregativos = new GenericLazyDataModel<VinculoEmpregativo>(vinculoEmpregativoService);
	}

	public void salvar() {
		vinculoEmpregativoService.salvar(vinculoEmpregativo);
		novo();
		infoSucesso();
	}

	public void novo() {
		vinculoEmpregativo = new VinculoEmpregativo();
	}

	public void excluir(VinculoEmpregativo p) {
		vinculoEmpregativoService.excluir(p.getId());
		infoSucesso();
	}

	public void editar(VinculoEmpregativo p) {
		vinculoEmpregativo = vinculoEmpregativoService.buscarPorId(p.getId());
	}

	public void pesquisar() {
		Map<String, Object> filtros = new HashMap<String, Object>();

		if (filtroNome != null && !filtroNome.isEmpty()) {
			filtros.put("nome", filtroNome);
		}

		vinculoEmpregativos.setFixedFilters(filtros);
	}

	public VinculoEmpregativo getVinculoEmpregativo() {
		return vinculoEmpregativo;
	}

	public void setVinculoEmpregativo(VinculoEmpregativo vinculoEmpregativo) {
		this.vinculoEmpregativo = vinculoEmpregativo;
	}

	public LazyDataModel<VinculoEmpregativo> getVinculoEmpregativos() {
		return vinculoEmpregativos;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}
	
	
	@Override
	public String getDescricaoTela() {
		return "Realize o gerenciamento de cadastro dos vínculos empretaticíos.";
	}

}
