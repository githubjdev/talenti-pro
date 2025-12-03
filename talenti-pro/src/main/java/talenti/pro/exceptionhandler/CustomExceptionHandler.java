package talenti.pro.exceptionhandler;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.exception.ExceptionUtils;

import jakarta.faces.FacesException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.application.ViewExpiredException;
import jakarta.faces.context.ExceptionHandler;
import jakarta.faces.context.ExceptionHandlerWrapper;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ExceptionQueuedEvent;
import jakarta.faces.event.ExceptionQueuedEventContext;

@SuppressWarnings("deprecation")
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

	private final ExceptionHandler wrapped;

	private static final Logger LOGGER = Logger.getLogger(CustomExceptionHandler.class.getName());

	public CustomExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() throws FacesException {
		Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();

		while (i.hasNext()) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
			Throwable t = context.getException();

			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();

			try {

	            // 🔥 Captura a causa raiz
	            String mensagemOriginal = ExceptionUtils.getRootCauseMessage(t);

	            // 🔥 Pega stack completo (para log)
	            String stackLog = ExceptionUtils.getStackTrace(t);
	            LOGGER.severe(stackLog);

	            String amigavel = traduzirErro(mensagemOriginal);

				// Mostrar mensagem para o usuário (via FacesMessage)
				fc.getExternalContext().getFlash().setKeepMessages(true);
	            fc.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, amigavel, amigavel));


				// Se quiser mostrar mensagem no console:
				t.printStackTrace();

				if (t instanceof ViewExpiredException) {
					NavigationHandler nav = fc.getApplication().getNavigationHandler();
					nav.handleNavigation(fc, null, "/pages/erro.xhtml?faces-redirect=true");
				} else {
					// Guardar no Flash
					ec.getFlash().put("msgErro", amigavel);
					ec.getFlash().setKeepMessages(true); // mantém mensagens após redirect
					ec.redirect(ec.getRequestContextPath() + "/pages/erro.xhtml");
				}

				fc.renderResponse();
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "Erro ao redirecionar após exceção", e);
			} finally {
				i.remove();
			}
		}

		getWrapped().handle();
	}

	// =============================================================
	// TRADUZ MENSAGENS DO BANCO PARA O USUÁRIO
	// =============================================================
	private String traduzirErro(String mensagem) {

		if (mensagem == null)
			return "Ocorreu um erro inesperado.";

		String msg = mensagem.toLowerCase();

		if (msg.contains("violates foreign key")) {
			return "Este registro não pode ser excluído porque está sendo usado em outra parte do sistema.";
		}

		if (msg.contains("unique constraint") || msg.contains("duplic")) {
			return "Já existe um registro com estas informações.";
		}

		if (msg.contains("not-null") || msg.contains("null value")) {
			return "Existem campos obrigatórios que não foram preenchidos.";
		}

		if (msg.contains("could not execute batch")) {
			return "Falha ao executar operação no banco. Verifique dependências relacionadas.";
		}

		if (msg.contains("syntax error") || msg.contains("invalid input")) {
			return "Os dados informados são inválidos.";
		}

		return mensagem; // fallback: mostra mensagem exata
	}
}
