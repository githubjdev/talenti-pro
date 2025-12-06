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
import talenti.pro.model.cadastro.Cargo;
import talenti.pro.service.cadastro.CargoService;

@Named
@ApplicationScoped
@FacesConverter(value = "cargoConverter", managed = true)
public class CargoConverter implements Converter<Cargo> {

	@Inject
	private CargoService cargoService;

	@Override
	public Cargo getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.trim().isEmpty()) {
			try {
				return cargoService.buscarPorId(Long.parseLong(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid cargo."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Cargo value) {
		if (value != null) {
			return String.valueOf(value.getId());
		} else {
			return null;
		}
	}

}
