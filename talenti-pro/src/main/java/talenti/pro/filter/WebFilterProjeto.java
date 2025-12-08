package talenti.pro.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class WebFilterProjeto implements Filter {
	
	private final Pattern acessoParcialPages = Pattern.compile("^/(pa|pag|page|pages[^/].*)$");
	private final Pattern diretorioSemPagina = Pattern.compile("^/pages/.+/$");

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String ctx = req.getContextPath();
		String url = req.getRequestURI().substring(ctx.length());
		boolean logado = req.getUserPrincipal() != null;

		if (url.startsWith("/javax.faces.resource")) {
			chain.doFilter(request, response);
			return;
		}

		if (url.equals("/") || url.isEmpty()) {
			resp.sendRedirect(ctx + (logado ? "/pages/index.xhtml" : "/publico/login.xhtml"));
			return;
		}

		if (acessoParcialPages.matcher(url).matches()) {
			resp.sendRedirect(ctx + (logado ? "/pages/index.xhtml" : "/publico/login.xhtml"));
			return;
		}

		if (url.equals("/pages") || url.equals("/pages/")) {
			resp.sendRedirect(ctx + (logado ? "/pages/index.xhtml" : "/publico/login.xhtml"));
			return;
		}

		if (diretorioSemPagina.matcher(url).matches()) {
			resp.sendRedirect(ctx + (logado ? "/pages/index.xhtml" : "/publico/login.xhtml"));
			return;
		}

		if (url.startsWith("/publico/")) {
			// se estiver logado e tentar ir no login
			if (logado && url.startsWith("/publico/login")) {
				resp.sendRedirect(ctx + "/pages/index.xhtml");
				return;
			}
			chain.doFilter(request, response);
			return;
		}

		if (url.startsWith("/pages/") && !logado) {
			resp.sendRedirect(ctx + "/publico/login.xhtml");
			return;
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void destroy() {
	}
}
