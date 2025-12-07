package talenti.pro.controller;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named(value = "loginController")
@ViewScoped
public class LoginController extends ManagedBeanController {

	private static final long serialVersionUID = 1L;

	@Override
	public String getDescricaoTela() {
		return "Realize o login no sistema com seu usuário e senha de acesso.";
	}

}
