package talenti.pro.filter;

import java.io.IOException;

import jakarta.inject.Inject;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import talenti.pro.exceptionhandler.ErroInternoException;
import talenti.pro.service.VersionadoService;

@WebFilter("/*")
public class WebFilterProjeto implements Filter {

	@Inject
	private VersionadoService versionadoService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		try {
			versionadoService.processarSQL(filterConfig.getServletContext());
		} catch (Throwable e) {
			throw new ErroInternoException("Erro ao processar SQL de versionamento", e);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
