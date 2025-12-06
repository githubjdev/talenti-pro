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
import talenti.pro.model.cadastro.VinculoEmpregativo;
import talenti.pro.service.cadastro.VinculoEmpregativoService;

@Named
@ApplicationScoped
@FacesConverter(value = "vinculo_empregativoConverter", managed = true)
public class VinculoEmpregativoConverter implements Converter<VinculoEmpregativo> {

	@Inject
	private VinculoEmpregativoService vinculo_empregativoService;

	@Override
	public VinculoEmpregativo getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.trim().isEmpty()) {
			try {
				return vinculo_empregativoService.buscarPorId(Long.parseLong(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error",
						"Not a valid vinculo empregativo."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, VinculoEmpregativo value) {
		if (value != null) {
			return String.valueOf(value.getId());
		} else {
			return null;
		}
	}

}
