package br.tec.ici.saude.Dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class MedicamentoMaisPrescritoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Long totalPrescricoes;

	public MedicamentoMaisPrescritoDTO(Long id, String nome, Long totalPrescricoes) {
		this.id = id;
		this.nome = nome;
		this.totalPrescricoes = totalPrescricoes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getTotalPrescricoes() {
		return totalPrescricoes;
	}

	public void setTotalPrescricoes(Long totalPrescricoes) {
		this.totalPrescricoes = totalPrescricoes;
	}

}
