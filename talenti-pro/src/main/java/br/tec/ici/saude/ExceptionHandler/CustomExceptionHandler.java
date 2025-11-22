package br.tec.ici.saude.ExceptionHandler;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.faces.FacesException;
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
				LOGGER.log(Level.SEVERE, "Erro capturado pelo ExceptionHandler: {0}", t.getMessage());
				t.printStackTrace();

				if (t instanceof ViewExpiredException) {
					NavigationHandler nav = fc.getApplication().getNavigationHandler();
					nav.handleNavigation(fc, null, "/pages/erro.xhtml?faces-redirect=true");
				} else {
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
}
