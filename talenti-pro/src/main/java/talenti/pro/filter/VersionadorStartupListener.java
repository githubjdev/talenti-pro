package talenti.pro.filter;

import java.io.FileNotFoundException;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import talenti.pro.exceptionhandler.ExceptionTalentiPro;
import talenti.pro.exceptionhandler.NegocioException;
import talenti.pro.service.VersionadoService;

@WebListener
public class VersionadorStartupListener implements ServletContextListener {

    @Inject
    private VersionadoService versionadoService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
			versionadoService.processarSQL(sce.getServletContext());
		} catch (ExceptionTalentiPro | FileNotFoundException e) {
			throw new NegocioException("Erro ao processar SQL de versinamento e atualização do banco de dados.");
		}
    }

}