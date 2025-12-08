package talenti.pro.controller;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.PermitAll;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@PermitAll
@Named(value = "indexBean")
@ViewScoped
public class IndexBean extends ManagedBeanController {

	private static final long serialVersionUID = 1L;

	private String descricao;

	@PostConstruct
	public void postConstruct() {
		this.descricao = "Projeto com configuração inicial.";
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	@Override
	public String getDescricaoTela() {
		return "Sistema avaliativo de colaboradores públicos.";
	}

}
