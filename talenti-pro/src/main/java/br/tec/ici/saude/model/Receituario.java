package br.tec.ici.saude.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "receituario")
public class Receituario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name = "fk_paciente"))
	private Paciente paciente;

	@OneToMany(mappedBy = "receituario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MedicamentoReceita> medicamentos = new ArrayList<MedicamentoReceita>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<MedicamentoReceita> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<MedicamentoReceita> medicamentos) {
		this.medicamentos = medicamentos;
	}

}
