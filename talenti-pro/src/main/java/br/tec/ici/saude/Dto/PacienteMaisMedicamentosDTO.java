package br.tec.ici.saude.Dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PacienteMaisMedicamentosDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idPaciente;
	private String nomePaciente;
	private Long totalMedicamentos;

	public PacienteMaisMedicamentosDTO(Long idPaciente, String nomePaciente, Long totalMedicamentos) {
		super();
		this.idPaciente = idPaciente;
		this.nomePaciente = nomePaciente;
		this.totalMedicamentos = totalMedicamentos;
	}

	public Long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public Long getTotalMedicamentos() {
		return totalMedicamentos;
	}

	public void setTotalMedicamentos(Long totalMedicamentos) {
		this.totalMedicamentos = totalMedicamentos;
	}

}
