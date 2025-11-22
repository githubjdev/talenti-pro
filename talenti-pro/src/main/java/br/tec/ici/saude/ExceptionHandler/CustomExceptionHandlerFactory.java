package br.tec.ici.saude.ExceptionHandler;

import jakarta.faces.context.ExceptionHandler;
import jakarta.faces.context.ExceptionHandlerFactory;

@SuppressWarnings("deprecation")
public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {

	 private final ExceptionHandlerFactory parent;

		public CustomExceptionHandlerFactory(ExceptionHandlerFactory parent) {
	        this.parent = parent;
	    }

	    @Override
	    public ExceptionHandler getExceptionHandler() {
	        return new CustomExceptionHandler(parent.getExceptionHandler());
	    }
}
