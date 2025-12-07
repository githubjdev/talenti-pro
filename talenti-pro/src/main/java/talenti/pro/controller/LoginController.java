package talenti.pro.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;

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
			HttpServletRequest req = (HttpServletRequest) 
								 FacesContext.getCurrentInstance()
								.getExternalContext()
								.getRequest();

			 req.login(login, senha);

			return "/pages/index.xhtml?faces-redirect=true";

		} catch (Exception e) {
			erro("Login Inválido: " + ExceptionUtils.getRootCauseMessage(e));
			return null;
		}
	}

	public void logout() throws Exception {

		HttpServletRequest request = getRequest();
		FacesContext context = getFacesContext();
		request.logout();
		context.getExternalContext().invalidateSession();
		context.getExternalContext().redirect(request.getContextPath() + "/publico/login.xhtml?faces-redirect=true");
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
