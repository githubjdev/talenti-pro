package br.tec.ici.saude.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicamento_receita")
public class MedicamentoReceita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String obs;

	@Column(nullable = false)
	private Double quantidade;

	@ManyToOne
	@JoinColumn(name = "id_medicamento", nullable = false, foreignKey = @ForeignKey(name = "fk_medicamento"))
	private Medicamento medicamento;

	@ManyToOne
	@JoinColumn(name = "id_receituario", nullable = false, foreignKey = @ForeignKey(name = "fk_receituario"))
	private Receituario receituario;

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Receituario getReceituario() {
		return receituario;
	}

	public void setReceituario(Receituario receituario) {
		this.receituario = receituario;
	}

}
