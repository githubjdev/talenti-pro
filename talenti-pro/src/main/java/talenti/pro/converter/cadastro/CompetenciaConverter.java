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
import talenti.pro.model.cadastro.Competencia;
import talenti.pro.service.cadastro.CompetenciaService;

@Named
@ApplicationScoped
@FacesConverter(value = "competenciaConverter", managed = true)
public class CompetenciaConverter implements Converter<Competencia> {

	@Inject
	private CompetenciaService competenciaService;

	@Override
	public Competencia getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.trim().isEmpty()) {
			try {
				return competenciaService.buscarPorId(Long.parseLong(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid competencia."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Competencia value) {
		if (value != null) {
			return String.valueOf(value.getId());
		} else {
			return null;
		}
	}

}
