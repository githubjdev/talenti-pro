package talenti.pro.service;

import java.util.Optional;

import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import talenti.pro.model.Usuario;
import talenti.pro.repository.GenericRepositoryImpl;

@PermitAll
@RequestScoped
public class LoginService extends GenericRepositoryImpl<Usuario> {

	public LoginService() {
		super(Usuario.class);
	}
	
	
	public String getSchemaBdPrefeitura(String login) {
		try {
			TypedQuery<String> query = em.createQuery("select u.prefeitura.schema_db_name from "
		                               + getEntityName() + " u where u.login = :login",
		                               String.class);
			query.setParameter("login", login);
			query.setMaxResults(1);
			return Optional.ofNullable(query.getSingleResult()).orElse("public");
		} catch (NoResultException e) {
			return "public";
		}
	}

}
