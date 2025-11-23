package talenti.pro.application;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@ViewScoped
@Named(value = "indexBean")
public class IndexBean implements Serializable {

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

}
