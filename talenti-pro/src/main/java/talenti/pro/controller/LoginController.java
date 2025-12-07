package talenti.pro.controller;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

@Named(value = "loginController")
@ViewScoped
public class LoginController extends ManagedBeanController {

	private static final long serialVersionUID = 1L;

	private String login;
	private String senha;

	  public String doLogin() {
	        try {
	            HttpServletRequest req = 
	                (HttpServletRequest) FacesContext.getCurrentInstance()
	                  .getExternalContext().getRequest();

	            req.login(login, senha);

	            return "/pages/index.xhtml?faces-redirect=true";

	        } catch (Exception e) {
	        	  e.printStackTrace();
	            erro("Login inválido");
	            return null;
	        }
	    }

	public void logout() throws Exception {
		getRequest().logout();
		getFacesContext().getExternalContext().invalidateSession();
		getFacesContext().getExternalContext().redirect("../publico/login.xhtml");
	}

	@Override
	public String getDescricaoTela() {
		return "Realize o login no sistema com seu usuário e senha de acesso.";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
