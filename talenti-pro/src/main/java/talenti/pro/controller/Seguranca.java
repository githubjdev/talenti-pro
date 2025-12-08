package talenti.pro.controller;

import java.io.Serializable;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

@Named(value = "seguranca")
@RequestScoped
public class Seguranca implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private HttpServletRequest request;

	public boolean admin() {
		return request.isUserInRole("ROLE_ADMIN");
	}

	public boolean gestor() {
		return request.isUserInRole("ROLE_GESTOR");
	}

	public boolean mediador() {
		return request.isUserInRole("ROLE_MEDIADOR");
	}

	public boolean colaborador() {
		return request.isUserInRole("ROLE_COLABORADOR");
	}

	public boolean user() {
		return request.isUserInRole("ROLE_USER");
	}

	public boolean master() {
		return request.isUserInRole("ROLE_MASTER");
	}

	public boolean tem(String role) {
		return request.isUserInRole(role);
	}

}
