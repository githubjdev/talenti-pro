package talenti.pro.model.avaliacao;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import talenti.pro.model.cadastro.Cargo;
import talenti.pro.model.cadastro.Competencia;
import talenti.pro.model.cadastro.Departamento;
import talenti.pro.model.cadastro.VinculoEmpregativo;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "avaliacao")
@SequenceGenerator(name = "seq_avaliacao", sequenceName = "seq_avaliacao", allocationSize = 1, initialValue = 1)
public class Avaliacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_avaliacao")
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false, columnDefinition = "text")
	private String descricao;

	@Column(nullable = false)
	private LocalDate dataPrazoRealizacao;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "departamento_id", nullable = false, foreignKey = @ForeignKey(name = "departamento_fk"))
	private Departamento departamento;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "cargo_id", nullable = false, foreignKey = @ForeignKey(name = "cargo_fk"))
	private Cargo cargo;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "vinculo_empregativo_id", nullable = false, foreignKey = @ForeignKey(name = "vinculo_empregativo_fk"))
	private VinculoEmpregativo vinculoEmpregativo;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "competencia_id", nullable = false, foreignKey = @ForeignKey(name = "competencia_fk"))
	private Competencia competencia;
	
	
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataPrazoRealizacao() {
		return dataPrazoRealizacao;
	}

	public void setDataPrazoRealizacao(LocalDate dataPrazoRealizacao) {
		this.dataPrazoRealizacao = dataPrazoRealizacao;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public VinculoEmpregativo getVinculoEmpregativo() {
		return vinculoEmpregativo;
	}

	public void setVinculoEmpregativo(VinculoEmpregativo vinculoEmpregativo) {
		this.vinculoEmpregativo = vinculoEmpregativo;
	}

}
