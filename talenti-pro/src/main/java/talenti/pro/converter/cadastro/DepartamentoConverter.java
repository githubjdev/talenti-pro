package talenti.pro.converter.cadastro;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import talenti.pro.model.cadastro.Departamento;
import talenti.pro.service.cadastro.DepartamentoService;

@Named
@ApplicationScoped
@FacesConverter(value = "departamentoConverter", managed = true)
public class DepartamentoConverter implements Converter<Departamento> {

	@Inject
	private DepartamentoService departamentoService;

	@Override
	public Departamento getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.trim().isEmpty()) {
			try {
				return departamentoService.buscarPorId(Long.parseLong(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid departamento."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Departamento value) {
		if (value != null) {
			return String.valueOf(value.getId());
		} else {
			return null;
		}
	}

}
