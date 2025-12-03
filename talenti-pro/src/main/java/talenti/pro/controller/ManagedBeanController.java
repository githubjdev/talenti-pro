package talenti.pro.controller;

import java.io.Serializable;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public abstract class ManagedBeanController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public abstract String getDescricaoTela();

	public void info(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", msg));
	}

	public void infoSucesso() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Operação realizada!"));
	}

}
