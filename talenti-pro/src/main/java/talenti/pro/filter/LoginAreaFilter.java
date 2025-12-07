package talenti.pro.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/publico/*")
public class LoginAreaFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getRequestURI();
        boolean logado = req.getUserPrincipal() != null;

        boolean eRecursoJSF = path.contains("javax.faces.resource");
        boolean eLogin = path.contains("/publico/login");
        boolean ePublico = path.contains("/publico/");
        boolean eRootContext = path.equals(req.getContextPath() + "/");

        // Permite JSF carregar CSS, JS, imagens
        if (eRecursoJSF) {
            chain.doFilter(request, response);
            return;
        }

        // 1 — ACESSO DIRETO AO CONTEXTO RAIZ
        if (eRootContext) {
            if (logado) {
                resp.sendRedirect(req.getContextPath() + "/pages/index.xhtml");
            } else {
                resp.sendRedirect(req.getContextPath() + "/publico/login.xhtml");
            }
            return;
        }

        // 2 — SE JÁ ESTIVER LOGADO E TENTAR IR AO LOGIN → REDIRECIONA PARA HOME
        if (logado && eLogin) {
            resp.sendRedirect(req.getContextPath() + "/pages/index.xhtml");
            return;
        }

        // 3 — SE NÃO ESTÁ LOGADO E A PÁGINA NÃO É PÚBLICA → FORÇA LOGIN
        if (!logado && !ePublico) {
            resp.sendRedirect(req.getContextPath() + "/publico/login.xhtml");
            return;
        }

        // 4 — Caso contrário, libera o acesso
        chain.doFilter(request, response);
	}
}
