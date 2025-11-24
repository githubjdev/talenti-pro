package talenti.pro.converter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import talenti.pro.model.Gestor;
import talenti.pro.service.GestorService;

@Named
@ApplicationScoped
@FacesConverter(value = "gestorConverter", managed = true)
public class GestorConverter implements Converter<Gestor> {

	@Inject
	private GestorService gestorService;

	@Override
	public Gestor getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.trim().isEmpty()) {
			try {
				return gestorService.buscarPorId(Long.parseLong(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid country."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Gestor value) {
		if (value != null) {
			return String.valueOf(value.getId());
		} else {
			return null;
		}
	}

}
