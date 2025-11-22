package br.tec.ici.saude.ManagedBean;

import java.io.Serializable;
import java.util.List;

import br.tec.ici.saude.Dto.MedicamentoMaisPrescritoDTO;
import br.tec.ici.saude.Repository.ReceituarioRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named(value = "relatorioMedicamentoManagedBean")
@ViewScoped
public class RelatorioMedicamentoManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ReceituarioRepository receituarioRepository;

	private List<MedicamentoMaisPrescritoDTO> medicamentos;

	@PostConstruct
	public void init() {
		medicamentos = receituarioRepository.medicamentoMaisPrescritos();
	}

	public List<MedicamentoMaisPrescritoDTO> getMedicamentos() {
		return medicamentos;
	}

}
