package br.tec.ici.saude.Filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter("/*")
public class DatabaseInitFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(DatabaseInitFilter.class.getName());

	private EntityManagerFactory emf;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			emf = Persistence.createEntityManagerFactory("talenti-pro-pu");
			EntityManager em = emf.createEntityManager();
			em.createNativeQuery("SELECT 1").getSingleResult();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			chain.doFilter(request, response);
		} catch (Throwable ex) {
			LOGGER.log(Level.SEVERE, "Erro interceptado no filtro global", ex);
			throw new ServletException(ex);
		}
	}

	@Override
	public void destroy() {
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
	}
}
