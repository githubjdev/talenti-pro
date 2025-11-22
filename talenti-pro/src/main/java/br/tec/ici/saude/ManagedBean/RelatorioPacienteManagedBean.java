package br.tec.ici.saude.ManagedBean;

import java.io.Serializable;
import java.util.List;

import br.tec.ici.saude.Dto.MedicamentoMaisPrescritoDTO;
import br.tec.ici.saude.Dto.PacienteMaisMedicamentosDTO;
import br.tec.ici.saude.Repository.PacienteRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named(value = "relatorioPacienteManagedBean")
@ViewScoped
public class RelatorioPacienteManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PacienteRepository pacienteRepository;

	private List<PacienteMaisMedicamentosDTO> pacientes;

	private List<MedicamentoMaisPrescritoDTO> pacientesFull;

	@PostConstruct
	public void init() {
		pacientes = pacienteRepository.buscarTopPacientesMaisMedicados();
		pacientesFull = pacienteRepository.listarPacientesComTotalMedicamentos();
	}

	public List<PacienteMaisMedicamentosDTO> getPacientes() {
		return pacientes;
	}

	public List<MedicamentoMaisPrescritoDTO> getPacientesFull() {
		return pacientesFull;
	}

}
